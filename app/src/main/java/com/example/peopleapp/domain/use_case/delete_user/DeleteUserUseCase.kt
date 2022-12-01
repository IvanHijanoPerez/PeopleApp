package com.example.peopleapp.domain.use_case.delete_user

import com.example.peopleapp.domain.model.User
import com.example.peopleapp.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        return repository.deleteUser(user)
    }

}