// ITpmsService.aidl
package com.automotive.bootcamp.example.sensor.tpms;

// Declare any non-default types here with import statements

// Import the TpmsInfo parcelable
import com.automotive.bootcamp.example.domain.model.TpmsInfo;
// Import the listener interface
import com.automotive.bootcamp.example.sensor.tpms.ITpmsListener;

interface ITpmsService {
    /**
     * Retrieves the latest TPMS information for a specific tire synchronously.
     * The client will block until this method returns.
     *
     * @param tireName The name/identifier of the tire (e.g., "frontLeft").
     * @return The TpmsInfo for the requested tire, or null if not found.
     */
    TpmsInfo getTpmsInfoForTire(String tireName);

    /**
     * Retrieves all available TPMS information synchronously.
     *
     * @return A list of all TpmsInfo objects.
     */
    List<TpmsInfo> getAllTpmsInfo();

    /**
     * Registers a listener to receive asynchronous updates for TPMS information.
     *
     * @param listener The ITpmsListener instance provided by the client.
     */
    void registerTpmsListener(ITpmsListener listener);

    /**
     * Unregisters a previously registered TPMS listener.
     *
     * @param listener The ITpmsListener instance to unregister.
     */
    void unregisterTpmsListener(ITpmsListener listener);
}