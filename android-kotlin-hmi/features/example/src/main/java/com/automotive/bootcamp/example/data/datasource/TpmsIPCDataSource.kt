package com.automotive.bootcamp.example.data.datasource

import com.automotive.bootcamp.example.data.datasource.service.ConnectionStatus
import com.automotive.bootcamp.example.data.datasource.service.ITpmsClient
import com.automotive.bootcamp.example.domain.model.TpmsInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class TpmsIPCDataSource(
    private val tpmsServiceClient: ITpmsClient,
    private val ioDispatcher: CoroutineDispatcher
) : TpmsDataSource {

    private val connectionMutex = Mutex()

    override suspend fun getTpmsInfo(): List<TpmsInfo> {
        connectIfNeeded()
        return tpmsServiceClient.getAllTpmsInfo() ?: emptyList()
    }

    override fun observeTpmsInfo(): Flow<List<TpmsInfo>> {
        return tpmsServiceClient.tpmsUpdates
            .onStart { connectIfNeeded() }
    }

    private suspend fun connectIfNeeded() {
        // Read the status once before potentially acquiring the lock
        val currentStatus = tpmsServiceClient.connectionStatus.value

        // Only proceed if not already connected or connecting
        if (needsConnection(currentStatus)) {
            connectionMutex.withLock {
                // Double-check after acquiring the lock, as status might have changed
                // by another coroutine that acquired the lock first.
                val statusAfterLock = tpmsServiceClient.connectionStatus.value
                if (needsConnection(statusAfterLock)) {
                    // Perform the connection on the IO dispatcher, just for example purposes
                    withContext(ioDispatcher) {
                        tpmsServiceClient.connect()
                    }
                }
            }
        }
    }

    // Helper function to centralize the logic for determining if a connection is needed
    private fun needsConnection(status: ConnectionStatus): Boolean {
        return status == ConnectionStatus.DISCONNECTED || status is ConnectionStatus.ERROR
    }
}