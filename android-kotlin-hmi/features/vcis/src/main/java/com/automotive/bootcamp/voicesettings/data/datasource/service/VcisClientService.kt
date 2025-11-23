package com.automotive.bootcamp.voicesettings.data.datasource.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.automotive.bootcamp.voicesettings.IVcisService
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class VcisClientService(
    private val context: Context,
    private val serviceIntent: Intent,
    private val ioDispatcher: CoroutineDispatcher
) : IVcisClient {
    private var iVcisService: IVcisService? = null

    private val _connectionStatus =
        MutableStateFlow<ConnectionStatus>(ConnectionStatus.DISCONNECTED)
    override val connectionStatus: StateFlow<ConnectionStatus> = _connectionStatus.asStateFlow()

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, service: IBinder?) {
            Log.d(TAG, "VCIS Service connected.")
            iVcisService = IVcisService.Stub.asInterface(service)
            _connectionStatus.value = ConnectionStatus.CONNECTED
        }

        override fun onServiceDisconnected(componentName: ComponentName?) {
            Log.w(
                TAG,
                "VCIS Service disconnected unexpectedly." +
                        "Current status: ${_connectionStatus.value}"
            )
            iVcisService = null
            // Only update if not already an error or explicitly disconnected
            if (!_connectionStatus.value.isError() || !_connectionStatus.value.isDisconnected()) {
                _connectionStatus.value = ConnectionStatus.DISCONNECTED
            }
        }

        override fun onBindingDied(name: ComponentName?) {
            Log.e(TAG, "VCIS Service onBindingDied.")
            iVcisService = null
            _connectionStatus.value = ConnectionStatus.ERROR("Binding died.")
        }

        override fun onNullBinding(name: ComponentName?) {
            Log.e(
                TAG,
                "VCIS Service onNullBinding. Service might not be available or misconfigured."
            )
            _connectionStatus.value =
                ConnectionStatus.ERROR("Null binding (service not found or misconfigured).")
        }
    }

    /**
     * Connects to the VCIS Service.
     * This will initiate the binding process.
     * Observe [connectionStatus] for the outcome.
     */
    override fun connect() {
        when (_connectionStatus.value) {
            is ConnectionStatus.DISCONNECTED, is ConnectionStatus.ERROR -> {
                Log.d(TAG, "Connecting to VCIS service...")
                _connectionStatus.value = ConnectionStatus.CONNECTING
                val boundSuccessfully =
                    context.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
                if (!boundSuccessfully) {
                    Log.e(
                        TAG,
                        "Failed to initiate binding to VCIS service with intent: $serviceIntent"
                    )
                    _connectionStatus.value = ConnectionStatus.ERROR("Failed to initiate bind.")
                }
            }

            is ConnectionStatus.CONNECTED -> {
                Log.d(TAG, "Already connected to VCIS service.")
            }

            is ConnectionStatus.CONNECTING -> {
                Log.d(TAG, "Currently attempting to connect to VCIS service.")
            }
        }
    }

    /**
     * Disconnects from the VCIS service.
     * This will unbind the service.
     */
    override fun disconnect() {
        when (_connectionStatus.value) {
            is ConnectionStatus.CONNECTED, is ConnectionStatus.CONNECTING -> {
                _connectionStatus.value = ConnectionStatus.DISCONNECTED // Set status first
                context.unbindService(serviceConnection)
                iVcisService = null
                Log.d(TAG, "Successfully disconnected from VCIS service.") // Log after actions
            }

            else -> {
                Log.d(TAG, "Not connected or connecting to VCIS service ")
            }
        }
    }

    override suspend fun getAllVoiceCategories(): List<VoiceCategory>? {
        return executeRemoteCall { iVcisService?.allVoiceCategories }
    }

    private suspend fun <T> executeRemoteCall(remoteCall: suspend () -> T?): T? {
        if (!_connectionStatus.value.isConnected() || iVcisService == null) {
            return null
        }
        return try {
            withContext(ioDispatcher) { // Execute blocking AIDL call on IO dispatcher
                remoteCall()
            }
        } catch (ex: RemoteException) {
            _connectionStatus.value = ConnectionStatus.ERROR("RemoteException: ${ex.message}")
            null
        }
    }

    companion object {
        private const val TAG = "VcisClientService"
    }
}

// Sealed class to represent connection status
sealed class ConnectionStatus {
    data object CONNECTED : ConnectionStatus()
    data object CONNECTING : ConnectionStatus()
    data object DISCONNECTED : ConnectionStatus()
    data class ERROR(val msg: String) : ConnectionStatus()
}

// Extension functions to check connection status
fun ConnectionStatus.isConnected(): Boolean = this == ConnectionStatus.CONNECTED
fun ConnectionStatus.isConnecting(): Boolean = this == ConnectionStatus.CONNECTING
fun ConnectionStatus.isDisconnected(): Boolean = this == ConnectionStatus.DISCONNECTED
fun ConnectionStatus.isError(): Boolean = this is ConnectionStatus.ERROR
