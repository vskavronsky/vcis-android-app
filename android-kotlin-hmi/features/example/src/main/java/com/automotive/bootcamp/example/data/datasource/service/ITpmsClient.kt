package com.automotive.bootcamp.example.data.datasource.service

import com.automotive.bootcamp.example.domain.model.TpmsInfo
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for interacting with a TPMS (Tire Pressure Monitoring System) service.
 * Provides methods to connect/disconnect, retrieve TPMS data, and listen for updates.
 */
interface ITpmsClient {

    /**
     * Initiates a connection to the TPMS service.
     * Observe [connectionStatus] for the outcome of the connection attempt.
     */
    fun connect()

    /**
     * Disconnects from the TPMS service.
     * This will unbind from the service and stop any active listeners.
     */
    fun disconnect()

    /**
     * A [StateFlow] indicating the current connection status to the TPMS service.
     * Consumers can collect this flow to react to connection changes.
     */
    val connectionStatus: StateFlow<ConnectionStatus>

    /**
     * A [StateFlow] that emits the latest [TpmsInfo] update received from the service
     * when a listener is active. Emits `null` if no listener is active or no update
     * has been received yet.
     */
    val tpmsUpdates: StateFlow<List<TpmsInfo>>

    /**
     * Asynchronously retrieves the latest TPMS information for a specific tire.
     * This is a suspending function and should be called from a coroutine.
     *
     * @param tireName The name or identifier of the tire (e.g., "frontLeft").
     * @return The [TpmsInfo] for the requested tire, or `null` if not found,
     *         the service is not connected, or an error occurs.
     */
    suspend fun getTpmsInfoForTire(tireName: String): TpmsInfo?

    /**
     * Asynchronously retrieves all available TPMS information from the service.
     * This is a suspending function and should be called from a coroutine.
     *
     * @return A list of all [TpmsInfo] objects, or `null` if the service is not
     *         connected or an error occurs.
     */
    suspend fun getAllTpmsInfo(): List<TpmsInfo>?
}
