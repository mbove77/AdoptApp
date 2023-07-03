package com.bove.martin.adoptapp.di

import com.bove.martin.adoptapp.data.AuthRepository
import com.bove.martin.adoptapp.data.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Martín Bove on 03/07/2023.
 * E-mail: mbove77@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepo(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}