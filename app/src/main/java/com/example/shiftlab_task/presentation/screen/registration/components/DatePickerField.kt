package com.example.shiftlab_task.presentation.screen.registration.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shiftlab_task.R
import com.example.shiftlab_task.common.DateFormatter.formatDate
import com.example.shiftlab_task.common.DateFormatter.formatDateToISO8601
import com.example.shiftlab_task.domain.state.RegistrationState
import com.example.shiftlab_task.presentation.screen.registration.RegistrationIntent
import com.example.shiftlab_task.presentation.screen.registration.RegistrationViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    labelText: String,
    viewModel: RegistrationViewModel,
    state: RegistrationState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = state.date,
                readOnly = true,
                onValueChange = {
                    viewModel.processIntent(RegistrationIntent.UpdateBirthday(state.birthday, it))
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.processIntent(RegistrationIntent.UpdateDatePickerVisibility)
                        }
                    ) {
                        Icon (
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                label = {Text (text = labelText)},
                textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W400)
            )

            if (state.isDatePickerOpened) {
                val datePickerState = rememberDatePickerState()

                DatePickerDialog(
                    onDismissRequest = {
                        viewModel.processIntent(RegistrationIntent.UpdateDatePickerVisibility)
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                val date = datePickerState.selectedDateMillis?.let { Date(it) }
                                viewModel.processIntent(RegistrationIntent.UpdateBirthday(
                                    formatDateToISO8601(date),
                                    formatDate(date)
                                ))
                                viewModel.processIntent(
                                    RegistrationIntent.UpdateDatePickerVisibility
                                )
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.ok),
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                viewModel.processIntent(RegistrationIntent.UpdateDatePickerVisibility)
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.cancel),
                            )
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState
                    )
                }
            }
        }
    }
}