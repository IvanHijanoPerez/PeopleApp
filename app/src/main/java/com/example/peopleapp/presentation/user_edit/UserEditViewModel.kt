package com.example.peopleapp.presentation.user_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peopleapp.domain.model.User
import com.example.peopleapp.domain.use_case.get_user.GetUserUseCase
import com.example.peopleapp.domain.use_case.insert_user.InsertUserUseCase
import com.example.peopleapp.presentation.user_list.UserListEvent
import com.example.peopleapp.presentation.user_list.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserEditViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserEditState())
    val state: State<UserEditState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentUserId: Int? = null

    init {
        savedStateHandle.get<Int>("userId")?.let { userId ->
            if (userId != -1) {
                viewModelScope.launch {
                    getUserUseCase(userId)?.also { user ->
                        currentUserId = user.id
                        _state.value = state.value.copy(
                            name = user.name,
                            lastName = user.lastName,
                            age = user.age.toString()
                        )

                    }
                }
            }
        }
    }

    fun onEvent(event: UserEditEvent) {
        when (event) {
            is UserEditEvent.EnteredName -> {
                _state.value = state.value.copy(name= event.name, lastName = state.value.lastName, age = state.value.age)
            }
            is UserEditEvent.EnteredLastName -> {
                _state.value = state.value.copy(name= state.value.name, lastName = event.lastName, age = state.value.age)
            }
            is UserEditEvent.EnteredAge -> {
                _state.value = state.value.copy(name= state.value.name, lastName = state.value.lastName, age = event.age)
            }
            is UserEditEvent.InsertUser -> {
                viewModelScope.launch {
                    insertUserUseCase(
                        User(
                            name = state.value.name,
                            lastName = state.value.lastName,
                            age = state.value.age.toInt(),
                            id = currentUserId
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveUser)
                }
            }
        }
    }

    sealed class UiEvent {
        object SaveUser: UiEvent()
    }

}