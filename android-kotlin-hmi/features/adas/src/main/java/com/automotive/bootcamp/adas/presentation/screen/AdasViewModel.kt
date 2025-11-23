package com.automotive.bootcamp.adas.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.domain.repository.AdasRepository
import com.automotive.bootcamp.adas.presentation.state.AdasFeatureState
import com.automotive.bootcamp.adas.presentation.ui.asCardUi
import com.automotive.bootcamp.adas.presentation.ui.asTileUi
import com.automotive.bootcamp.adas.presentation.ui.getUiConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdasViewModel @Inject constructor(
    private val repository: AdasRepository,
) : ViewModel() {

    private val _features = MutableStateFlow<List<AdasFeatureState>>(emptyList())
    val features: StateFlow<List<AdasFeatureState>> = _features

    init {
        Log.d(TAG, "init")
        viewModelScope.launch {
            repository.getFeatures().collect { features ->
                _features.value = features.map { featureType ->
                    AdasFeatureState(
                        type = featureType,
                        config = featureType.getUiConfig(),
                        onToggle = { onEvent(AdasEvent.FeatureToggle(featureType)) },
                        onSelectMode = { newMode ->
                            onEvent(AdasEvent.SetOperatingMode(featureType, newMode))
                        }
                    )
                }
            }
        }
    }

    fun loadFeatureSettings(type: AdasFeatureType) {
        // Skip if already loaded
        if (_features.value.find { it.type == type }?.cardConfig != null) return

        viewModelScope.launch {
            repository.getFeatureSettings(type).collect { settings ->

                _features.value = features.value.map { state ->
                    if (state.type == type) {
                        state.copy(
                            tileConfig = settings.asTileUi(),
                            cardConfig = settings.asCardUi())
                    } else {
                        state
                    }
                }
            }
        }
    }

    private companion object {
        val TAG: String = AdasViewModel::class.java.name
    }

    fun onEvent(event: AdasEvent) {

        when (event) {
            is AdasEvent.FeatureToggle -> {
                viewModelScope.launch {
                    repository.switchFeatureState(event.featureType)
                }
            }

            is AdasEvent.SetOperatingMode -> {
                viewModelScope.launch {
                    repository.setFeatureMode(event.featureType, event.mode)
                }
            }
        }
    }
}

sealed class AdasEvent {
    data class FeatureToggle(val featureType: AdasFeatureType) : AdasEvent()
    data class SetOperatingMode(
        val featureType: AdasFeatureType,
        val mode: AdasFeatureSettings.OperatingMode,
    ) : AdasEvent()
}
