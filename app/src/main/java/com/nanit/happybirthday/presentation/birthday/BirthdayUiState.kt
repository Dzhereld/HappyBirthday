package com.nanit.happybirthday.presentation.birthday

import com.nanit.happybirthday.domain.entity.BirthdayEvent
import com.nanit.happybirthday.presentation.common.ScreenUiState

sealed interface BirthdayUiState : ScreenUiState {
    data object Empty : BirthdayUiState
    data class Error(val oldData: BirthdayEvent) : BirthdayUiState
    data class Filled(val data: BirthdayEvent) : BirthdayUiState
}