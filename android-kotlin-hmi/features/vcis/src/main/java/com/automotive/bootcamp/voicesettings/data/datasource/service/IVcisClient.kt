package com.automotive.bootcamp.voicesettings.data.datasource.service

import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for interacting with a VCIS (Voice Control Integration Settings) service.
 * Provides methods to connect/disconnect, retrieve VCIS data.
 */
interface IVcisClient {

    /**
     * Initiates a connection to the VCIS service.
     * Observe [connectionStatus] for the outcome of the connection attempt.
     */
    fun connect()

    /**
     * Disconnects from the VCIS service.
     * This will unbind from the service.
     */
    fun disconnect()

    /**
     * A [StateFlow] indicating the current connection status to the VCIS service.
     * Consumers can collect this flow to react to connection changes.
     */
    val connectionStatus: StateFlow<ConnectionStatus>

    /**
     * Asynchronously retrieves all available [VoiceCategory] information from the service.
     * This is a suspending function and should be called from a coroutine.
     *
     * @return A list of all [VoiceCategory] objects, or `null` if the service is not
     *         connected or an error occurs.
     */
    suspend fun getAllVoiceCategories(): List<VoiceCategory>?
}
