package com.example.peopleapp.domain.use_case.get_user

import com.example.peopleapp.domain.model.User
import com.example.peopleapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: Int): User? {
        return repository.getUserById(id)
    }

}