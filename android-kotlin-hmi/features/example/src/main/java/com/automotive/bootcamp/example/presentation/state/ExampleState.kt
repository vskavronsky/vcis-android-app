package com.automotive.bootcamp.example.presentation.state

import com.automotive.bootcamp.example.domain.model.TpmsInfo
import com.automotive.bootcamp.example.domain.model.User
import com.automotive.bootcamp.example.domain.model.Warnings

data class ExampleUIState(
    val example: String,
    val result: String,
    val temp: String,
    val users: List<User>,
    val warnigs: List<Warnings>,
    val tpms: List<TpmsInfo>,
) {
    companion object {
        val Empty = ExampleUIState(
            "empty", "result",
            temp = "",
            users = emptyList(),
            warnigs = emptyList(),
            tpms = emptyList()
        )
    }
}

