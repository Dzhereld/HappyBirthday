package com.nanit.happybirthday.presentation.profile

import androidx.lifecycle.ViewModel
import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ThemeType
import com.nanit.happybirthday.domain.repository.ChildProfileRepository
import com.nanit.happybirthday.presentation.common.ScreenUiState
import com.nanit.happybirthday.presentation.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import javax.inject.Inject

@HiltViewModel
class ChildProfileViewModel @Inject constructor(private val repository: ChildProfileRepository) :
    ViewModel() {
    private var currentState = ChildProfile(
        name = "",
        ageInMonths = -1,
        theme = ThemeType.UNDEFINED,
    )

    val uiState: Flow<ScreenUiState> = repository.observeChildProfile()
        .map { childProfile ->
            currentState = childProfile
            ChildProfileUiState.Filled(data = childProfile)
        }.catch {
            ChildProfileUiState.Error(oldData = currentState)
        }

    fun handleUiEvent(uiEvent: UiEvent) {

    }
}