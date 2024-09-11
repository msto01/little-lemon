package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation (sharedPreferences: SharedPreferences, menuItems: List<MenuItemRoom>) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (sharedPreferences.contains("firstName") && sharedPreferences.contains("lastName") && sharedPreferences.contains("email")) Home.route else Onboarding.route
    ) {
        composable("Home") {
            Home(navController, menuItems)
        }
        composable("Onboarding") {
            Onboarding(navController, sharedPreferences)
        }
        composable("Profile") {
            Profile(navController, sharedPreferences)
        }
    }
}