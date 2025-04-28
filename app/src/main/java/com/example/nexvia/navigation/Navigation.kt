package com.example.nexvia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nexvia.ui.auth.LoginScreen
import com.example.nexvia.ui.auth.RegisterConductorScreen
import com.example.nexvia.ui.auth.RegisterPasajeroScreen
import com.example.nexvia.ui.home.HomeConductorScreen
import com.example.nexvia.ui.home.HomePasajeroScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.LOGIN) {
        composable(Destinations.LOGIN) { LoginScreen(navController) }
        composable(Destinations.REGISTER_PASAJERO) { RegisterPasajeroScreen(navController) }
        composable(Destinations.REGISTER_CONDUCTOR) { RegisterConductorScreen(navController) }
        composable(Destinations.HOME_PASAJERO) { HomePasajeroScreen(navController) }
        composable(Destinations.HOME_CONDUCTOR) { HomeConductorScreen(navController) }
    }
}

