package com.bove.martin.adoptapp.domain.usecases

import com.bove.martin.adoptapp.data.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

/**
 * Created by Martín Bove on 2/1/2023.
 * E-mail: mbove77@gmail.com
 */
class GetCurrentUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(): FirebaseUser? {
        return authRepository.currentUser
    }
}