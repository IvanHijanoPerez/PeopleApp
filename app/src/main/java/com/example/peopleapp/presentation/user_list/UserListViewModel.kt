package com.example.peopleapp.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peopleapp.domain.use_case.delete_user.DeleteUserUseCase
import com.example.peopleapp.domain.use_case.get_users.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _state = mutableStateOf(UserListState())
    val state: State<UserListState> = _state

    init {
        getUsersUseCase().onEach { users ->
            _state.value = state.value.copy(users = users)

        }.launchIn(viewModelScope)
    }

    fun onEvent(event: UserListEvent) {
        when (event) {
            is UserListEvent.DeleteUser -> {
                viewModelScope.launch {
                    deleteUserUseCase(event.user)
                }
            }
        }
    }
}