package com.automotive.bootcamp.userprofiles.presentation.state

import com.automotive.bootcamp.userprofiles.domain.model.ConfigurationInfo
import com.automotive.bootcamp.userprofiles.domain.model.User
import com.automotive.bootcamp.userprofiles.domain.model.UserId
import com.automotive.bootcamp.userprofiles.domain.model.Warnings

data class UserProfilesUIState(
    val userProfiles: String,
    val result: String,
    val temp: String,
    val users: List<User>,
    val warnings: List<Warnings>,
    val configuration: Map<UserId, ConfigurationInfo>,
    var currentUser: User?,
) {
    companion object {
        val Empty = UserProfilesUIState(
            "empty", "result",
            temp = "",
            users = emptyList(),
            warnings = emptyList(),
            configuration = emptyMap(),
            currentUser = null,
        )
    }
}
