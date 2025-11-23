package com.automotive.bootcamp.voicesettings.ipc

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.automotive.bootcamp.voicesettings.IVcisService
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory

class VcisServiceImpl : Service() {
    private val binder = VcisServiceBinder()
    private val mockVcisData = mutableListOf(
        VoiceCategory(
            "Navigation", "Route guidance & directions",
            "12 commands"
        ),
        VoiceCategory(
            "Media", "Music & entertainment",
            "8 commands"
        ),
        VoiceCategory(
            "Climate", "Temperature & air control",
            "6 commands"
        ),
        VoiceCategory(
            "Phone", "Calls & messages", "10 commands"
        ),
        VoiceCategory(
            "Vehicle", "Car settings & controls",
            "15 commands"
        )
    )

    // This is the core implementation of your AIDL interface
    inner class VcisServiceBinder : IVcisService.Stub() {
        override fun getAllVoiceCategories(): MutableList<VoiceCategory> {
            Log.d(TAG, "Binder getAllVoiceCategories()")
            return mockVcisData
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service Created")
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG, "Service Binded")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "Service unBinded")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service Destroyed")
    }

    companion object {
        private const val TAG = "VcisServiceImpl"
    }
}
