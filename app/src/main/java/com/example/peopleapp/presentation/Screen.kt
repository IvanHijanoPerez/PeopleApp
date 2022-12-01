package com.example.peopleapp.presentation

sealed class Screen(val route: String) {
    object UserListScreen : Screen("user_list")
    object UserEditScreen : Screen("user_edit?userId={userId}") {
        fun passId(userId: Int?): String {
            return "user_edit?userId=$userId"
        }
    }
}
