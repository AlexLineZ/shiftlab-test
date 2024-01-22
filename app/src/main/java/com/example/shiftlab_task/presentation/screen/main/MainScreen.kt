package com.example.shiftlab_task.presentation.screen.main

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shiftlab_task.R

@Composable
fun MainScreen(viewModel: MainViewModel){
    val focusManager = LocalFocusManager.current
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Button(
            onClick = {
                viewModel.processIntent(MainIntent.ClickOnButton)
            },
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                fontSize = 20.sp,
                text = stringResource(id = R.string.welcome)
            )
        }

        if (state.isAlertDialogVisible) {
            AlertDialog(
                onDismissRequest = {viewModel.processIntent(MainIntent.UpdateAlertDialogVisible)},
                title = {
                    Text(text = stringResource(id = R.string.welcome))
                },
                text = {
                    Text("Привет, ${state.userName}!")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.processIntent(MainIntent.UpdateAlertDialogVisible)
                        }
                    ) {
                        Text(stringResource(id = R.string.ok))
                    }
                }
            )
        }
    }
}