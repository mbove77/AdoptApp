package com.bove.martin.adoptapp.data

import com.bove.martin.adoptapp.common.Resource
import com.bove.martin.adoptapp.domain.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Martín Bove on 03/07/2023.
 * E-mail: mbove77@gmail.com
 */
class FakeAuthRepository(private val firebaseUser: FirebaseUser?) : AuthRepository {

    private var shouldReturnError: Boolean = false

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override val currentUser: FirebaseUser?
        get() =  if (shouldReturnError) null else firebaseUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return if (shouldReturnError) {
            Resource.Failure(Exception("Authentication error"))
        } else {
            Resource.Success(firebaseUser!!)
        }
    }

    override suspend fun googleLogin(task: Task<GoogleSignInAccount>): Resource<FirebaseUser> {
        return if (shouldReturnError) {
            Resource.Failure(Exception("Authentication error"))
        } else {
            Resource.Success(firebaseUser!!)
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
    ): Resource<FirebaseUser> {
        return if (shouldReturnError) {
            Resource.Failure(Exception("Authentication error"))
        } else {
            Resource.Success(firebaseUser!!)
        }
    }

    override fun logout() {}
}