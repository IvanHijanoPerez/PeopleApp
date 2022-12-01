package com.example.peopleapp.presentation.user_edit

sealed class UserEditEvent {
    data class EnteredName(val name: String) : UserEditEvent()
    data class EnteredLastName(val lastName: String) : UserEditEvent()
    data class EnteredAge(val age: String) : UserEditEvent()
    object InsertUser : UserEditEvent()
}
