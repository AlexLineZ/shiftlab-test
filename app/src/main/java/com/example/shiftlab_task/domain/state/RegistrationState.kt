package com.example.shiftlab_task.domain.state

data class RegistrationState (
    val firstName: String,
    val secondName: String,
    val birthday: String,
    val date: String,

    val isDatePickerOpened: Boolean,
    val isSecondScreenAvailable: Boolean,

    val password: String,
    val confirmPassword: String,

    val isPasswordHide: Boolean,
    val isConfirmPasswordHide: Boolean,

    val isError: Boolean,
    val isErrorPasswordText: String?,
    val isErrorFirstNameText: String?,
    val isErrorSecondNameText: String?,
    val isErrorEmailText: String?,
    val isErrorConfirmPasswordText: String?
)