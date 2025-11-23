package com.automotive.bootcamp.voicesettings.data.datasource

import android.util.Log
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VoiceCategoryMockDataSource : VoiceCategoryDataSource {
    private val _categories = MutableStateFlow<List<VoiceCategory>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        Log.d(TAG, "init categories")

        _categories.value =
            listOf(
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
    }

    override suspend fun getCategories(): Flow<List<VoiceCategory>> {
        Log.d(TAG, "return categories")
        return categories
    }

    private companion object {
        val TAG = VoiceCategoryMockDataSource::class.java.name
    }
}
