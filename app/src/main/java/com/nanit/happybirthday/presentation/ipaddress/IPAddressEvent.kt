package com.nanit.happybirthday.presentation.ipaddress

import com.nanit.happybirthday.presentation.common.UiEvent

sealed interface IPAddressUiEvent : UiEvent {
    data class OnConnectionClick(
        val ipAddress: String,
        val port: Int
    ) : IPAddressUiEvent
}