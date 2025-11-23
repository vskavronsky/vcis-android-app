package com.automotive.bootcamp.climate.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automotive.bootcamp.climate.presentation.state.ClimateUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.Either
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ClimateViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(ClimateUIState.Empty)
    val state: StateFlow<ClimateUIState> = _state

    init {
        Log.d(TAG, "init")
    }

    fun climateClick() {
        Log.d(TAG, "climateClick")
    }

    fun climateSuspendClick() {
        viewModelScope.launch {
            Log.d(TAG, "climateSuspendClick")

            climateResultUsage()
        }
    }

    private suspend fun climateResultUsage() {
    }

    private companion object {
        val TAG = ClimateViewModel::class.java.name
    }
}
