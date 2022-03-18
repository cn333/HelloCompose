package com.example.hello

import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home_screen") {
        composable(route = "home_screen") {
            Hello(navController = navController)
        }
        composable(
            route = "second_screen?name={name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType; defaultValue = "World"})
        ) {
            Greeting (navController = navController, name = it.arguments?.getString("name"))
        }
    }
}