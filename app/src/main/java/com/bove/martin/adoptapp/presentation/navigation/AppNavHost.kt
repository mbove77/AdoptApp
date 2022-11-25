package com.bove.martin.adoptapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bove.martin.adoptapp.presentation.HomeScreen
import com.bove.martin.adoptapp.presentation.SplashAnimation
import com.bove.martin.adoptapp.presentation.login.LoginScreen
import com.bove.martin.adoptapp.presentation.login.RegisterScreen

/**
 * Created by Martín Bove on 24/11/2022.
 * E-mail: mbove77@gmail.com
 */
@Composable
fun AppNavHost(
//    viewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            SplashAnimation(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
    }
}