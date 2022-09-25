package com.example.peopleapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.peopleapp.presentation.user_edit.UserEditScreen
import com.example.peopleapp.presentation.user_list.UserListScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.UserListScreen.route
    ) {
        composable(route = Screen.UserListScreen.route) {
            UserListScreen(navController = navController)
        }
        composable(
            route = Screen.UserEditScreen.route,
            arguments = listOf(
                navArgument(
                    name = "userId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            UserEditScreen(navController = navController)
        }
    }
}