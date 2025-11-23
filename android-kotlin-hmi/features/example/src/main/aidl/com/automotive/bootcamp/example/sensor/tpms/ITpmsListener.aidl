// ITpmsListener.aidl
package com.automotive.bootcamp.example.sensor.tpms;

// Import the TpmsInfo parcelable
import com.automotive.bootcamp.example.domain.model.TpmsInfo;

oneway interface ITpmsListener {
    /**
     * Called when new TPMS information is available.
     * This method is declared as 'oneway' to indicate that it's a non-blocking call
     * from the service to the client. The service doesn't wait for the client to return.
     */
    void onTpmsInfoUpdated(in List<TpmsInfo> data);
}