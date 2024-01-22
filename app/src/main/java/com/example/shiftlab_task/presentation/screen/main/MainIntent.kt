package com.example.shiftlab_task.presentation.screen.main

import com.example.shiftlab_task.presentation.screen.registration.RegistrationIntent

sealed class MainIntent {
    data object UpdateAlertDialogVisible : MainIntent()
}