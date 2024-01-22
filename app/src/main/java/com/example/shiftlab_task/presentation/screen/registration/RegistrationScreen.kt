package com.example.shiftlab_task.presentation.screen.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shiftlab_task.R
import com.example.shiftlab_task.presentation.screen.registration.components.CustomTextField
import com.example.shiftlab_task.presentation.screen.registration.components.DatePickerField
import com.example.shiftlab_task.presentation.screen.registration.components.PasswordCustomTextField

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel) {
    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.shift_logo),
            modifier = Modifier.size(128.dp),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.register),
            style = MaterialTheme.typography.titleLarge
        )

        CustomTextField(
            labelText = stringResource(id = R.string.first_name),
            value = state.firstName,
            onValueChange = { viewModel.processIntent(RegistrationIntent.UpdateFirstName(it)) },
            error = state.isErrorFirstNameText,
            modifier = Modifier
        )

        CustomTextField(
            labelText = stringResource(id = R.string.second_name),
            value = state.secondName,
            onValueChange = { viewModel.processIntent(RegistrationIntent.UpdateSecondName(it)) },
            error = state.isErrorSecondNameText,
            modifier = Modifier
        )

        DatePickerField(
            labelText = stringResource(id = R.string.birthday),
            viewModel = viewModel,
            state = state
        )

        PasswordCustomTextField(
            labelText = stringResource(id = R.string.password),
            value = state.password,
            onValueChange = { viewModel.processIntent(RegistrationIntent.UpdatePassword(it)) },
            transformationState = state.isPasswordHide,
            onButtonClick = {viewModel.processIntent(RegistrationIntent.UpdatePasswordVisibility)},
            errorText = state.isErrorPasswordText,
            modifier = Modifier
        )

        PasswordCustomTextField(
            labelText = stringResource(id = R.string.confirm_password),
            value = state.confirmPassword,
            onValueChange = {viewModel.processIntent(RegistrationIntent.UpdateConfirmPassword(it))},
            transformationState = state.isConfirmPasswordHide,
            onButtonClick = {
                viewModel.processIntent(RegistrationIntent.UpdateConfirmPasswordVisibility)
            },
            errorText = state.isErrorConfirmPasswordText,
            modifier = Modifier
        )

        Button(
            onClick = { viewModel.processIntent(RegistrationIntent.Registration(state)) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(top = 32.dp),
            enabled = state.isButtonAvailable
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W600
                )
            )
        }
    }
}