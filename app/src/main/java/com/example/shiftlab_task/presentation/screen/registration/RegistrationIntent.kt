package com.example.shiftlab_task.presentation.screen.registration

import com.example.shiftlab_task.common.Constants
import com.example.shiftlab_task.domain.state.RegistrationState
import com.example.shiftlab_task.domain.validator.Validator

sealed class RegistrationIntent {
    data class UpdateFirstName(val firstName: String) : RegistrationIntent()
    data class UpdateSecondName(val secondName: String) : RegistrationIntent()
    data class UpdateBirthday(val birthday: String, val date: String) : RegistrationIntent()
    data object UpdateDatePickerVisibility : RegistrationIntent()
    data class UpdatePassword(val password: String) : RegistrationIntent()
    data class UpdateConfirmPassword(val confirmPassword: String) : RegistrationIntent()
    data object UpdatePasswordVisibility : RegistrationIntent()
    data object UpdateConfirmPasswordVisibility : RegistrationIntent()
    data class Registration(val registrationState: RegistrationState): RegistrationIntent()
    data class UpdateErrorText(
        val validator: Validator,
        val data: String,
        val secondData: String = Constants.EMPTY_STRING
    ): RegistrationIntent()
}