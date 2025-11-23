package com.automotive.bootcamp.climate.presentation.state


data class ClimateUIState(
    val climate: String,
    val result: String,
    val temp: String,
) {
    companion object {
        val Empty = ClimateUIState(
            "empty", "result",
            temp = "",
        )
    }
}

