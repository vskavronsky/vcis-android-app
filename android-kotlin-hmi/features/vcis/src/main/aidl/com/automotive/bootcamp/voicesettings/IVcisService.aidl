// IVcisService.aidl
package com.automotive.bootcamp.voicesettings;

import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory;

interface IVcisService {
    /**
     * Retrieves all available VoiceCategory information synchronously.
     *
     * @return A list of all VoiceCategory objects.
     */
    List<VoiceCategory> getAllVoiceCategories();
}
