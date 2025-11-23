package com.automotive.bootcamp.adas.domain.repository.error

import utils.Error

enum class AdasError: Error {
    FEATURE_NOT_AVAILABLE,
    FEATURE_UPDATE_FAILED,
    NOTHING_TO_UPDATE,
    NOT_SUPPORTED
}