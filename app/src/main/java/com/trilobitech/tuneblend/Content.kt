package com.trilobitech.tuneblend

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trilobitech.tuneblend.login.LoginScreen

@Composable
fun Content(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            LoginScreen()
        }
    }
}
