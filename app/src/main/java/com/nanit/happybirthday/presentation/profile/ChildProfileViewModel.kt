package com.nanit.happybirthday.presentation.profile

import androidx.lifecycle.ViewModel
import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ThemeType
import com.nanit.happybirthday.domain.repository.ChildProfileRepository
import com.nanit.happybirthday.presentation.common.ScreenUiState
import com.nanit.happybirthday.presentation.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ChildProfileViewModel @Inject constructor(private val repository: ChildProfileRepository) :
    ViewModel() {
    private var currentProfile = ChildProfile(
        name = "",
        ageInMonths = -1,
        theme = ThemeType.UNDEFINED,
    )

    val uiState: Flow<ScreenUiState> = repository.observeBirthdayEvent()
        .map { result ->
            result.getOrNull()?.let { data ->
                currentProfile = data
                ChildProfileUiState.Filled(data = data)
            } ?: ChildProfileUiState.Error(oldData = currentProfile)
        }

    fun handleUiEvent(uiEvent: UiEvent) {

    }
}