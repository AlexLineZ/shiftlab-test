package com.example.shiftlab_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shiftlab_task.presentation.navigation.Destinations
import com.example.shiftlab_task.presentation.navigation.Navigation
import com.example.shiftlab_task.presentation.ui.theme.ShiftlabtaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShiftlabtaskTheme(darkTheme = false) {
                Navigation(startDestination = Destinations.REGISTRATION_SCREEN)
            }
        }
    }
}