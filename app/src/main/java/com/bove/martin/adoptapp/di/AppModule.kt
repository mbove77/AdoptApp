package com.bove.martin.adoptapp.di

import com.bove.martin.adoptapp.DefaultDispatchers
import com.bove.martin.adoptapp.DispatchersProvider
import com.bove.martin.adoptapp.common.AppConstants
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Martín Bove on 25/11/2022.
 * E-mail: mbove77@gmail.com
 */

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideGso(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(AppConstants.GOOGLE_APP_ID)
            .requestEmail()
            .build()
    }

    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersProvider {
        return DefaultDispatchers()
    }
}
