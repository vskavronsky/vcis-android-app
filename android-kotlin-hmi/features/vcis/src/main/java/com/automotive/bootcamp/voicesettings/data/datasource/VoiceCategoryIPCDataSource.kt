package com.automotive.bootcamp.voicesettings.data.datasource

import android.util.Log
import com.automotive.bootcamp.voicesettings.data.datasource.service.ConnectionStatus
import com.automotive.bootcamp.voicesettings.data.datasource.service.IVcisClient
import com.automotive.bootcamp.voicesettings.data.datasource.service.isConnected
import com.automotive.bootcamp.voicesettings.data.datasource.service.isConnecting
import com.automotive.bootcamp.voicesettings.data.datasource.service.isDisconnected
import com.automotive.bootcamp.voicesettings.data.datasource.service.isError
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class VoiceCategoryIPCDataSource(
    private val vcisClientService: IVcisClient,
    private val ioDispatcher: CoroutineDispatcher
) : VoiceCategoryDataSource {
    private val connectionMutex = Mutex()

    private val _categories = MutableStateFlow<List<VoiceCategory>>(emptyList())
    val categories = _categories.asStateFlow()

    override suspend fun getCategories(): Flow<List<VoiceCategory>> {
        Log.d(TAG, "Check if we need to connect to VCIS service.")
        connectIfNeeded()

        while (vcisClientService.connectionStatus.value.isConnecting()) {
            Log.d(TAG, "Connecting...")
            delay(200L)
        }

        if (vcisClientService.connectionStatus.value.isConnected()) {
            vcisClientService.getAllVoiceCategories()?.let {
                Log.d(TAG, "Getting VoiceCategories from VCIS service.")
                _categories.value = it
            }
        }

        if (vcisClientService.connectionStatus.value.isDisconnected()) {
            Log.d(TAG, "Disconnected from VCIS service.")
        }

        if (vcisClientService.connectionStatus.value.isError()) {
            Log.d(TAG, "Failed to connect to VCIS service.")
        }
        return categories
    }

    private suspend fun connectIfNeeded() {
        // Read the status once before potentially acquiring the lock
        val currentStatus = vcisClientService.connectionStatus.value

        // Only proceed if not already connected or connecting
        if (needsConnection(currentStatus)) {
            connectionMutex.withLock {
                // Double-check after acquiring the lock, as status might have changed
                // by another coroutine that acquired the lock first.
                val statusAfterLock = vcisClientService.connectionStatus.value
                if (needsConnection(statusAfterLock)) {
                    // Perform the connection on the IO dispatcher, just for example purposes
                    withContext(ioDispatcher) {
                        Log.d(TAG, "Perform connect to VCIS service.")
                        vcisClientService.connect()
                    }
                }
            }
        }
    }

    // Helper function to centralize the logic for determining if a connection is needed
    private fun needsConnection(status: ConnectionStatus): Boolean {
        return status == ConnectionStatus.DISCONNECTED || status is ConnectionStatus.ERROR
    }

    companion object {
        private const val TAG = "VoiceCategoryIPCDataSource"
    }
}
