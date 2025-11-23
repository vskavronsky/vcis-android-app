package com.automotive.bootcamp.example.data.datasource.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.automotive.bootcamp.example.domain.model.TpmsInfo
import com.automotive.bootcamp.example.sensor.tpms.ITpmsListener
import com.automotive.bootcamp.example.sensor.tpms.ITpmsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class TpmsServiceClient(
    private val context: Context,
    private val serviceIntent: Intent,
    private val ioDispatcher: CoroutineDispatcher,
) : ITpmsClient {
    private var iTpmsService: ITpmsService? = null

    private val _connectionStatus =
        MutableStateFlow<ConnectionStatus>(ConnectionStatus.DISCONNECTED)
    override val connectionStatus: StateFlow<ConnectionStatus> = _connectionStatus.asStateFlow()

    private val _tpmsUpdates = MutableStateFlow<List<TpmsInfo>>(emptyList())
    override val tpmsUpdates: StateFlow<List<TpmsInfo>> = _tpmsUpdates.asStateFlow()

    private val clientListener = object : ITpmsListener.Stub() {
        override fun onTpmsInfoUpdated(data: List<TpmsInfo>?) {
            data?.let { newTpmsData ->
                _tpmsUpdates.update { newTpmsData }
                Log.d(TAG, "onTpmsInfoUpdated: $newTpmsData")
            }
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "TPMS Service connected")
            iTpmsService = ITpmsService.Stub.asInterface(service)
            _connectionStatus.value = ConnectionStatus.CONNECTED
            iTpmsService?.registerTpmsListener(clientListener)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.w(
                TAG,
                "TPMS Service disconnected unexpectedly. Current status: ${_connectionStatus.value}"
            )
            iTpmsService = null
            // Only update if not already an error or explicitly disconnected
            if (!_connectionStatus.value.isError() && !_connectionStatus.value.isDisconnected()) {
                _connectionStatus.value = ConnectionStatus.DISCONNECTED
            }
        }

        override fun onBindingDied(name: ComponentName?) {
            Log.e(TAG, "TPMS Service binding died.")
            iTpmsService = null
            _connectionStatus.value = ConnectionStatus.ERROR("Binding died")
        }

        override fun onNullBinding(name: ComponentName?) {
            Log.e(
                TAG,
                "TPMS Service onNullBinding. Service might not be available or misconfigured."
            )
            _connectionStatus.value =
                ConnectionStatus.ERROR("Null binding (service not found or misconfigured)")
        }
    }

    /**
     * Connects to the TPMS Service.
     * This will initiate the binding process.
     * Observe [connectionStatus] for the outcome.
     */
    override fun connect() {
        when (_connectionStatus.value) {
            is ConnectionStatus.DISCONNECTED, is ConnectionStatus.ERROR -> {
                Log.d(TAG, "Connecting to TPMS Service...")
                _connectionStatus.value = ConnectionStatus.CONNECTING
                val boundSuccessfully = context.bindService(
                    serviceIntent,
                    serviceConnection,
                    Context.BIND_AUTO_CREATE
                )
                if (!boundSuccessfully) {
                    Log.e(
                        TAG,
                        "Failed to initiate binding to TPMS Service with intent: $serviceIntent"
                    )
                    _connectionStatus.value = ConnectionStatus.ERROR("Failed to initiate bind")
                }
            }

            is ConnectionStatus.CONNECTED -> {
                Log.d(TAG, "Already connected to TPMS Service.")
            }

            is ConnectionStatus.CONNECTING -> {
                Log.d(TAG, "Currently attempting to connect to TPMS Service.")
            }
        }
    }

    /**
     * Disconnects from the TPMS Service.
     * This will unbind the service.
     */
    override fun disconnect() {
        when (_connectionStatus.value) {
            ConnectionStatus.CONNECTED, ConnectionStatus.CONNECTING -> {
                _connectionStatus.value = ConnectionStatus.DISCONNECTED // Set status first
                iTpmsService?.unregisterTpmsListener(clientListener)
                context.unbindService(serviceConnection)
                iTpmsService = null
                Log.d(TAG, "Successfully disconnected from TPMS Service.") // Log after actions
            }

            else -> {
                Log.d(TAG, "Not connected or connecting to TPMS Service ")
            }
        }
    }

    override suspend fun getTpmsInfoForTire(tireName: String): TpmsInfo? {
        return executeRemoteCall {
            iTpmsService?.getTpmsInfoForTire(tireName)
        }
    }

    override suspend fun getAllTpmsInfo(): List<TpmsInfo>? {
        return executeRemoteCall {
            iTpmsService?.allTpmsInfo
        }
    }

    private suspend fun <T> executeRemoteCall(
        remoteCall: suspend () -> T?
    ): T? {
        if (!_connectionStatus.value.isConnected() || iTpmsService == null) {
            return null
        }
        return try {
            withContext(ioDispatcher) { // Execute blocking AIDL call on IO dispatcher
                remoteCall()
            }
        } catch (e: RemoteException) {
            _connectionStatus.value = ConnectionStatus.ERROR("RemoteException: ${e.message}")
            null
        }
    }

    companion object {
        private const val TAG = "TpmsServiceClient"
    }
}

// Sealed class to represent connection status
sealed class ConnectionStatus {
    data object CONNECTED : ConnectionStatus()
    data object DISCONNECTED : ConnectionStatus()
    data object CONNECTING : ConnectionStatus()
    data class ERROR(val message: String) : ConnectionStatus()
}

// Extension functions to check connection status
fun ConnectionStatus.isConnected(): Boolean = this == ConnectionStatus.CONNECTED
fun ConnectionStatus.isDisconnected(): Boolean = this == ConnectionStatus.DISCONNECTED
fun ConnectionStatus.isConnecting(): Boolean = this == ConnectionStatus.CONNECTING
fun ConnectionStatus.isError(): Boolean = this is ConnectionStatus.ERROR


