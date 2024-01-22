package com.example.shiftlab_task.presentation.screen.registration

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.shiftlab_task.common.Constants
import com.example.shiftlab_task.data.model.UserModel
import com.example.shiftlab_task.data.repository.UserRepositoryImpl
import com.example.shiftlab_task.domain.repository.UserRepository
import com.example.shiftlab_task.domain.state.RegistrationState
import com.example.shiftlab_task.domain.usecase.DataValidateUseCase
import com.example.shiftlab_task.domain.usecase.SaveUserDataUseCase
import com.example.shiftlab_task.domain.validator.ConfirmPasswordValidator
import com.example.shiftlab_task.domain.validator.FirstNameValidator
import com.example.shiftlab_task.domain.validator.PasswordValidator
import com.example.shiftlab_task.domain.validator.SecondNameValidator
import com.example.shiftlab_task.presentation.navigation.router.AppRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegistrationViewModel(
    private val context: Context,
    private val router: AppRouter
) : ViewModel() {
    private val emptyState = RegistrationState(
        firstName = Constants.EMPTY_STRING,
        secondName = Constants.EMPTY_STRING,
        birthday = Constants.EMPTY_STRING,
        date = Constants.EMPTY_STRING,
        isDatePickerOpened = Constants.FALSE,
        isSecondScreenAvailable = Constants.FALSE,
        isButtonAvailable = Constants.FALSE,
        password = Constants.EMPTY_STRING,
        confirmPassword = Constants.EMPTY_STRING,
        isPasswordHide = Constants.FALSE,
        isConfirmPasswordHide = Constants.FALSE,
        isError = Constants.FALSE,
        isErrorFirstNameText = null,
        isErrorSecondNameText = null,
        isErrorEmailText = null,
        isErrorPasswordText = null,
        isErrorConfirmPasswordText = null
    )
    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<RegistrationState> get() = _state

    private val userRepository = UserRepositoryImpl(context)
    private val dataValidateUseCase = DataValidateUseCase()
    private val saveUserDataUseCase = SaveUserDataUseCase(userRepository)
    fun processIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.UpdateBirthday -> {
                _state.value = state.value.copy(birthday = intent.birthday)
                _state.value = state.value.copy(date = intent.date)
            }
            is RegistrationIntent.UpdateDatePickerVisibility -> {
                _state.value = state.value.copy(
                    isDatePickerOpened = !_state.value.isDatePickerOpened
                )
            }
            is RegistrationIntent.UpdateFirstName -> {
                _state.value = state.value.copy(firstName = intent.firstName.trim())
                processIntent(
                    RegistrationIntent.UpdateErrorText(FirstNameValidator(), intent.firstName)
                )
                processIntent(RegistrationIntent.UpdateButtonAvailable)
            }
            is RegistrationIntent.UpdateSecondName -> {
                _state.value = state.value.copy(secondName = intent.secondName.trim())
                processIntent(RegistrationIntent.UpdateErrorText(
                    SecondNameValidator(),
                    intent.secondName)
                )
            }
            is RegistrationIntent.UpdateConfirmPassword -> {
                _state.value = state.value.copy(confirmPassword = intent.confirmPassword.trim())
                processIntent(
                    RegistrationIntent.UpdateErrorText(ConfirmPasswordValidator(),
                        intent.confirmPassword,
                        state.value.password
                    )
                )
            }
            is RegistrationIntent.UpdatePassword -> {
                _state.value = state.value.copy(password = intent.password.trim())
                processIntent(
                    RegistrationIntent.UpdateErrorText(PasswordValidator(), intent.password)
                )
            }
            is RegistrationIntent.UpdateConfirmPasswordVisibility -> {
                _state.value = state.value.copy(
                    isConfirmPasswordHide = !_state.value.isConfirmPasswordHide
                )
            }
            is RegistrationIntent.UpdatePasswordVisibility -> {
                _state.value = state.value.copy(
                    isPasswordHide = !_state.value.isPasswordHide
                )
            }
            is RegistrationIntent.Registration -> {
                val user = UserModel(
                    firstName = state.value.firstName,
                    secondName = state.value.secondName,
                    birthday = state.value.birthday,
                    password = state.value.password
                )
                saveUserDataUseCase.saveUser(user = user)
                router.toMain()
            }

            is RegistrationIntent.UpdateErrorText -> {
                val result = dataValidateUseCase.invoke(
                    intent.validator,
                    intent.data,
                    intent.secondData
                )
                when (intent.validator) {
                    is PasswordValidator -> _state.value = state.value.copy (
                        isErrorPasswordText = result?.let { context.getString(it) }
                    )
                    is ConfirmPasswordValidator -> _state.value = state.value.copy (
                        isErrorConfirmPasswordText = result?.let { context.getString(it) }
                    )
                    is FirstNameValidator -> _state.value = state.value.copy(
                        isErrorFirstNameText = result?.let { context.getString(it) }
                    )
                    is SecondNameValidator -> _state.value = state.value.copy(
                        isErrorSecondNameText = result?.let { context.getString(it) }
                    )
                }
            }

            is RegistrationIntent.UpdateButtonAvailable -> {
                if (hasNotErrors() && !hasEmpty()) {
                    _state.value = state.value.copy(
                        isButtonAvailable = true
                    )
                } else {
                    _state.value = state.value.copy(
                        isButtonAvailable = false
                    )
                }
            }
        }
    }

    private fun hasNotErrors(): Boolean{
        return state.value.isErrorFirstNameText == null
                && state.value.isErrorSecondNameText == null
                && state.value.isErrorPasswordText == null
                && state.value.isErrorConfirmPasswordText == null
    }

    private fun hasEmpty(): Boolean {
        return state.value.firstName.isNotEmpty()
                || state.value.secondName.isNotEmpty()
                || state.value.birthday.isNotEmpty()
                || state.value.password.isNotEmpty()
                || state.value.confirmPassword.isNotEmpty()
    }
}