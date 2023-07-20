package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.common.Resource
import com.bove.martin.adoptapp.domain.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

/**
 * Created by Martín Bove on 2/1/2023.
 * E-mail: mbove77@gmail.com
 */
class LoginWithGoogleUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(task: Task<GoogleSignInAccount>): Resource<FirebaseUser> {
        return authRepository.googleLogin(task)
    }
}