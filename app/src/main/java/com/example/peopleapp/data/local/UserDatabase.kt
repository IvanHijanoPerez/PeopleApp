package com.example.peopleapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.peopleapp.data.local.dao.UserDao
import com.example.peopleapp.domain.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}