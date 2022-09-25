package com.example.peopleapp.domain.use_case.get_users

import com.example.peopleapp.domain.model.User
import com.example.peopleapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }

}