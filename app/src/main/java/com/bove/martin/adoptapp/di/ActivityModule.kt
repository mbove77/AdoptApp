package com.bove.martin.adoptapp.di

import android.content.Context
import androidx.activity.result.ActivityResultRegistry
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

/**
 * Created by Martín Bove on 11/06/2023.
 * E-mail: mbove77@gmail.com
 */
@Module
@InstallIn(ActivityComponent::class)
class  ActivityModule {
    @Provides
    fun provideActivityResultRegistry(@ActivityContext activity: Context): ActivityResultRegistry =
        (activity as? AppCompatActivity)?.activityResultRegistry
            ?: throw IllegalArgumentException("You must use AppCompatActivity")
}