package com.automotive.bootcamp.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize // Use kotlinx.parcelize for easy Parcelable implementation
data class TpmsInfo(val tire: String, val pressure: Double) : Parcelable
