package com.automotive.bootcamp.voicesettings.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VoiceCategory(
    val name: String,
    val description: String,
    val commandsNumber: String
) : Parcelable
