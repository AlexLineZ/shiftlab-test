package com.example.shiftlab_task.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shiftlab_task.data.localstorage.LocalStorage
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
fun Navigation(){
    val navController = rememberNavController()

    val registrationViewModel = RegistrationViewModel(
        LocalContext.current,
        AppRouter(navController)
    )
    val mainViewModel = MainViewModel(LocalContext.current)

    NavHost(
        navController = navController,
        startDestination = chooseStartDestination(LocalContext.current)
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

private fun chooseStartDestination(context: Context): String{
    val hasData = LocalStorage(context).hasData()
    return if (hasData){
        Destinations.MAIN_SCREEN
    } else {
        Destinations.REGISTRATION_SCREEN
    }
}

