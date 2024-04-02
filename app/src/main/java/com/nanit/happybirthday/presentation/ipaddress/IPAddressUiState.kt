package com.nanit.happybirthday.presentation.ipaddress

sealed interface IPAddressUiState {
    data object Empty : IPAddressUiState

    data object Loading : IPAddressUiState
    data object Success : IPAddressUiState
    data object ErrorConnection : IPAddressUiState

    data object ErrorTheme : IPAddressUiState
}
