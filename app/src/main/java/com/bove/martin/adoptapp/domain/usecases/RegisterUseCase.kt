package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.common.Resource
import com.bove.martin.adoptapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

/**
 * Created by Martín Bove on 2/1/2023.
 * E-mail: mbove77@gmail.com
 */
class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String): Resource<FirebaseUser> {
        return authRepository.register(name, email, password)
    }
}