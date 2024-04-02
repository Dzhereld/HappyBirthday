package com.nanit.happybirthday.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nanit.happybirthday.domain.repository.ChildProfileRepository
import com.nanit.happybirthday.presentation.common.ScreenUiState
import com.nanit.happybirthday.presentation.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChildProfileViewModel @Inject constructor(private val repository: ChildProfileRepository) :
    ViewModel() {

    private var _uiState = mutableStateOf<ChildProfileUiState>(ChildProfileUiState.Empty)
    val uiState: State<ScreenUiState> = _uiState

    fun handleUiEvent(uiEvent: UiEvent) {

    }
}