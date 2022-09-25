package com.example.peopleapp.presentation.user_edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.peopleapp.domain.use_case.get_user.GetUserUseCase
import com.example.peopleapp.domain.use_case.insert_user.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserEditViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

}