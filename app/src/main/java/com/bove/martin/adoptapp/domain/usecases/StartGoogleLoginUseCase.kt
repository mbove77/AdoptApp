package com.bove.martin.adoptapp.domain.usecases

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.bove.martin.adoptapp.AppConstants.Companion.GOOGLE_REQ_ID
import com.bove.martin.adoptapp.data.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by Martín Bove on 2/1/2023.
 * E-mail: mbove77@gmail.com
 */
class StartGoogleLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext val context: Context,
    private val gso: GoogleSignInOptions
) {
    operator fun invoke(activity: Activity) {
        val client = GoogleSignIn.getClient(context, gso)

        val loginIntent: Intent = client.signInIntent
        activity.startActivityForResult(loginIntent, GOOGLE_REQ_ID)
    }
}