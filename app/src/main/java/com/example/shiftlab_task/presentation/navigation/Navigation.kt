package com.example.shiftlab_task.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shiftlab_task.presentation.navigation.router.AppRouter
import com.example.shiftlab_task.presentation.screen.main.MainScreen
import com.example.shiftlab_task.presentation.screen.main.MainViewModel
import com.example.shiftlab_task.presentation.screen.registration.RegistrationScreen
import com.example.shiftlab_task.presentation.screen.registration.RegistrationViewModel

object Destinations {
    const val REGISTRATION_SCREEN = "registrationScreen"
    const val MAIN_SCREEN = "mainScreen"
}

@Composable
fun Navigation(
    startDestination: String
){
    val navController = rememberNavController()

    val registrationViewModel = RegistrationViewModel(
        LocalContext.current,
        AppRouter(navController)
    )
    val mainViewModel = MainViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.REGISTRATION_SCREEN) {
            RegistrationScreen(
                viewModel = registrationViewModel
            )
        }
        composable(Destinations.MAIN_SCREEN){
            MainScreen(
                viewModel = mainViewModel
            )
        }
    }
}

