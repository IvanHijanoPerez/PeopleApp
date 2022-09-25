package com.example.peopleapp.presentation.user_list

import com.example.peopleapp.domain.model.User

data class UserListState(
    val users: List<User> = emptyList()
)
{
}