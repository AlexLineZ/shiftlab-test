package com.example.shiftlab_task.presentation.navigation.router

import androidx.navigation.NavController
import com.example.shiftlab_task.presentation.navigation.Destinations

class AppRouter(
    private val navController: NavController
) {
    fun toMain(){
        navController.navigate(Destinations.MAIN_SCREEN) {
            popUpTo(0)
        }
    }
}