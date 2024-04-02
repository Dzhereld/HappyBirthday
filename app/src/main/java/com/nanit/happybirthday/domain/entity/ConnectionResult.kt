package com.nanit.happybirthday.domain.entity

sealed interface ConnectionResult {
    data object Success
    data class Error(val message: String)
    data object Loading
}