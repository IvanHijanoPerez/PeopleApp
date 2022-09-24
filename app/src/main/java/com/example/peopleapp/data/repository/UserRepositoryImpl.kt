package com.example.peopleapp.data.repository

import com.example.peopleapp.data.local.dao.UserDao
import com.example.peopleapp.domain.model.User
import com.example.peopleapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : UserRepository {
    override fun getUsers(): Flow<List<User>> {
        return dao.getUsers()
    }

    override suspend fun getUserById(id: Int): User? {
        return dao.getUserById(id)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }
}