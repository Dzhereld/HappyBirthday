package com.nanit.happybirthday.presentation.birthday

import androidx.lifecycle.ViewModel
import com.nanit.happybirthday.domain.entity.BirthdayEvent
import com.nanit.happybirthday.domain.entity.ThemeType
import com.nanit.happybirthday.domain.repository.BirthdayRepository
import com.nanit.happybirthday.presentation.common.ScreenUiState
import com.nanit.happybirthday.presentation.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BirthdayViewModel @Inject constructor(private val repository: BirthdayRepository) :
    ViewModel() {
    private var currentBirthdayEvent = BirthdayEvent(
        name = "",
        ageInMonths = -1,
        theme = ThemeType.UNDEFINED,
    )

    val uiState: Flow<ScreenUiState> = repository.observeBirthdayEvent()
        .map { result ->
            result.getOrNull()?.let { data ->
                currentBirthdayEvent = data
                BirthdayUiState.Filled(data = data)
            } ?: BirthdayUiState.Error(oldData = currentBirthdayEvent)
        }

    fun handleUiEvent(uiEvent: UiEvent) {

    }
}