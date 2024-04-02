package com.nanit.happybirthday.presentation

import androidx.lifecycle.ViewModel
import com.nanit.happybirthday.domain.repository.ChildProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: ChildProfileRepository) : ViewModel() {

}