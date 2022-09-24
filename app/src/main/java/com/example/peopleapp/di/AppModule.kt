package com.example.peopleapp.di

import android.app.Application
import androidx.room.Room
import com.example.peopleapp.common.Constants
import com.example.peopleapp.data.local.UserDatabase
import com.example.peopleapp.data.repository.UserRepositoryImpl
import com.example.peopleapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }
}