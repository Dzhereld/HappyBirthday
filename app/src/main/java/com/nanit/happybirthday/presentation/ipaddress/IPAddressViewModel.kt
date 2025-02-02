package com.nanit.happybirthday.presentation.ipaddress

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanit.happybirthday.domain.entity.ThemeType
import com.nanit.happybirthday.domain.repository.BirthdayRepository
import com.nanit.happybirthday.presentation.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IPAddressViewModel @Inject constructor(private val repository: BirthdayRepository) :
    ViewModel() {

    private var _uiState = mutableStateOf<IPAddressUiState>(IPAddressUiState.Empty)
    val uiState: State<IPAddressUiState> = _uiState

    fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is IPAddressUiEvent.OnConnectionClick -> {
                connectToServer(
                    uiEvent.ipAddress,
                    uiEvent.port
                )
            }
        }
    }

    private fun connectToServer(ipAddress: String, port: Int) {
        viewModelScope.launch {
            _uiState.value = IPAddressUiState.Loading
            repository.connectToServer(ipAddress, port)
                .onSuccess {
                    _uiState.value = IPAddressUiState.Success
                    startObservingBirthdayEvent()
                }
                .onFailure {
                    _uiState.value = IPAddressUiState.ErrorConnection
                }
        }
    }

    private suspend fun startObservingBirthdayEvent() {
        repository.observeBirthdayEvent()
            .collect { result ->
                result.onSuccess { birthdayEvent ->
                    if (birthdayEvent.theme != ThemeType.UNDEFINED)
                        _uiState.value = IPAddressUiState.OpenBirthdayScreen
                    else
                        _uiState.value = IPAddressUiState.ErrorTheme
                }.onFailure {
                    _uiState.value = IPAddressUiState.ErrorTheme
                }
            }
    }

}