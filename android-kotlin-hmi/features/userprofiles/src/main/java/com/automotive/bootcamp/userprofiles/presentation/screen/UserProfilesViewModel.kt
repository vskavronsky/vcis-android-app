package com.automotive.bootcamp.userprofiles.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automotive.bootcamp.userprofiles.domain.repository.UserProfilesRepository
import com.automotive.bootcamp.userprofiles.presentation.state.UserProfilesUIState
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
class UserProfilesViewModel @Inject constructor(
    private val userProfilesRepository: UserProfilesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(UserProfilesUIState.Empty)
    val state: StateFlow<UserProfilesUIState> = _state

    init {
        Log.d(TAG, "init")

        viewModelScope.launch {
            merge(
                userProfilesRepository.userProfilesFlow.map { userProfilesData ->
                    { state: UserProfilesUIState ->
                        state.copy(userProfiles = userProfilesData.toString())
                    }
                },
                userProfilesRepository.getUsers().map { usersData ->
                    { state: UserProfilesUIState ->
                        state.copy(users = usersData)
                    }
                },
                userProfilesRepository.getWarnings().map { warningsData ->
                    { state: UserProfilesUIState ->
                        state.copy(warnings = warningsData)
                    }
                },
                userProfilesRepository.getConfigurationInfo().map { configurationData ->
                    { state: UserProfilesUIState ->
                        state.copy(configuration = configurationData)
                    }
                },
            ).collect { stateUpdater ->
                _state.update(stateUpdater)
            }
        }
    }

    fun userProfilesClick() {
        Log.d(TAG, "userProfilesClick")

        userProfilesRepository.userProfilesFunction(data = Random.nextInt())
    }

    fun userProfilesSuspendClick() {
        viewModelScope.launch {
            Log.d(TAG, "userProfilesSuspendClick")

            val result = userProfilesRepository.userProfilesSuspendFunction(data = Random.nextInt())
            userProfilesResultUsage()
            _state.update { it.copy(result = result) }
        }
    }

    private suspend fun userProfilesResultUsage() {
        when (val result = userProfilesRepository.userProfilesResult(2)) {
            is Either.Error -> {
                Log.d(TAG, "error = ${result.error}")
            }
            is Either.Success -> {
                Log.d(TAG, "success = ${result.data}")
            }
        }
    }

    private companion object {
        val TAG = UserProfilesViewModel::class.java.name
    }
}
