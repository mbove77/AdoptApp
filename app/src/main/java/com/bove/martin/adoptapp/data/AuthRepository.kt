package com.bove.martin.adoptapp.data

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Martín Bove on 25/11/2022.
 * E-mail: mbove77@gmail.com
 */
interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun googleLogin(task: Task<GoogleSignInAccount>): Resource<FirebaseUser>
    suspend fun register(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}