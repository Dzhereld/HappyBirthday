package com.nanit.happybirthday.presentation.ipaddress

import com.nanit.happybirthday.presentation.common.ScreenUiState

sealed interface IPAddressUiState : ScreenUiState {
    data object Empty : IPAddressUiState

    data object Loading : IPAddressUiState
    data object Success : IPAddressUiState
    data object OpenBirthdayScreen : IPAddressUiState
    data object ErrorConnection : IPAddressUiState

    data object ErrorTheme : IPAddressUiState
}