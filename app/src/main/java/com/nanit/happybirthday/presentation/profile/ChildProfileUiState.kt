package com.nanit.happybirthday.presentation.profile

import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.presentation.common.ScreenUiState

sealed interface ChildProfileUiState : ScreenUiState {
    data object Empty : ChildProfileUiState
    data class Error(val oldData: ChildProfile) : ChildProfileUiState
    data class Filled(val data: ChildProfile) : ChildProfileUiState
}