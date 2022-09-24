package com.example.peopleapp.domain.repository

import com.example.peopleapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    suspend fun getUserById(id: Int): User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)
}