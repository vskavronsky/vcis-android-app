package com.automotive.bootcamp.example.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automotive.bootcamp.example.domain.repository.ExampleRepository
import com.automotive.bootcamp.example.presentation.state.ExampleUIState
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
class ExampleViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ExampleUIState.Empty)
    val state: StateFlow<ExampleUIState> = _state

    init {
        Log.d(TAG, "init")

        viewModelScope.launch {
            merge(
                exampleRepository.exampleFlow.map { exampleData ->
                    { state: ExampleUIState ->
                        state.copy(example = exampleData.toString())
                    }
                },
                exampleRepository.getUsers().map { usersData ->
                    { state: ExampleUIState ->
                        state.copy(users = usersData)
                    }
                },
                exampleRepository.getWarnings().map { warningsData ->
                    { state: ExampleUIState ->
                        state.copy(warnigs = warningsData)
                    }
                },
                exampleRepository.getTpmsInfo().map { tpmsData ->
                    { state: ExampleUIState ->
                        state.copy(tpms = tpmsData)
                    }
                },
            ).collect { stateUpdater ->
                _state.update(stateUpdater)
            }
        }
    }

    fun exampleClick() {
        Log.d(TAG, "exampleClick")

        exampleRepository.exampleFunction(data = Random.nextInt())
    }

    fun exampleSuspendClick() {
        viewModelScope.launch {
            Log.d(TAG, "exampleSuspendClick")

            val result = exampleRepository.exampleSuspendFunction(data = Random.nextInt())
            exampleResultUsage()
            _state.update { it.copy(result = result) }
        }
    }

    private suspend fun exampleResultUsage() {
        when (val result = exampleRepository.exampleResult(2)) {
            is Either.Error -> {
                Log.d(TAG, "error = ${result.error}")
            }
            is Either.Success -> {
                Log.d(TAG, "success = ${result.data}")
            }
        }
    }

    private companion object {
        val TAG = ExampleViewModel::class.java.name
    }
}