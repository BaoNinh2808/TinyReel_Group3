package com.tinyreel.authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.tinyreel.authentication.data.AuthRepository
import com.tinyreel.authentication.data.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}