package com.example.peopleapp.data.local.dao

import androidx.room.*
import com.example.peopleapp.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}