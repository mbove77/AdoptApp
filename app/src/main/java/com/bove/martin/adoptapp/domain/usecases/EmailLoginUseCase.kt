package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.data.AuthRepository
import com.bove.martin.adoptapp.data.Resource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

/**
 * Created by Martín Bove on 2/1/2023.
 * E-mail: mbove77@gmail.com
 */
class EmailLoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Resource<FirebaseUser> {
        return  authRepository.login(email, password)
    }
}