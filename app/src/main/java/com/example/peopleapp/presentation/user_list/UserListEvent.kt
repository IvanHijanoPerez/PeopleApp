package com.example.peopleapp.presentation.user_list

import com.example.peopleapp.domain.model.User

sealed class UserListEvent {
    data class DeleteUser(val user: User) : UserListEvent()
}
