package com.example.shiftlab_task.presentation.screen.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.shiftlab_task.common.Constants
import com.example.shiftlab_task.data.repository.UserRepositoryImpl
import com.example.shiftlab_task.domain.state.MainState
import com.example.shiftlab_task.domain.usecase.GetUserDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(private val context: Context) : ViewModel() {
    private val emptyState = MainState(
        isAlertDialogVisible = Constants.FALSE,
        userName = Constants.EMPTY_STRING
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<MainState> get() = _state

    private val userRepository = UserRepositoryImpl(context)
    private val getUserDataUseCase = GetUserDataUseCase(userRepository)

    fun processIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.ClickOnButton -> {
                _state.value = state.value.copy(
                    userName = getUserName()
                )
                processIntent(MainIntent.UpdateAlertDialogVisible)
            }

            MainIntent.UpdateAlertDialogVisible -> {
                _state.value = state.value.copy(
                    isAlertDialogVisible = !_state.value.isAlertDialogVisible
                )
            }
        }
    }

    private fun getUserName(): String{
        return getUserDataUseCase.getUserName()
    }
}