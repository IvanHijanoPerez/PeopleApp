package com.example.peopleapp.domain.use_case.insert_user

import com.example.peopleapp.domain.model.User
import com.example.peopleapp.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        repository.insertUser(user)
    }

}