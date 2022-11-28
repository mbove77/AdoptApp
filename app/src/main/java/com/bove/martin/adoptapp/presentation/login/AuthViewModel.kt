package com.bove.martin.adoptapp.presentation.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bove.martin.adoptapp.AppConstants.Companion.GOOGLE_APP_ID
import com.bove.martin.adoptapp.AppConstants.Companion.GOOGLE_REQ_ID
import com.bove.martin.adoptapp.data.AuthRepository
import com.bove.martin.adoptapp.data.Resource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Martín Bove on 25/11/2022.
 * E-mail: mbove77@gmail.com
 */

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext val context : Context
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _registerFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val registerFlow: StateFlow<Resource<FirebaseUser>?> = _registerFlow

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(GOOGLE_APP_ID)
        .requestEmail()
        .build()

    // TODO Ver como se puede manejar el problema de la llamada doble a Google
    private var googleSignInIsInit = false

    val currentUser: FirebaseUser?
        get() = authRepository.currentUser

    init {
        if (currentUser != null) {
            _loginFlow.value = Resource.Success(authRepository.currentUser!!)
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = authRepository.login(email, password)
        _loginFlow.value = result
    }

    fun googleLogin(activity: Activity) {
        if (!googleSignInIsInit) {
            googleSignInIsInit = true
            _loginFlow.value = Resource.Loading

            val client = GoogleSignIn.getClient(context, gso)

            val loginIntent: Intent = client.signInIntent
            activity.startActivityForResult(loginIntent, GOOGLE_REQ_ID)
        }
    }

    fun finishGoogleLogin(task: Task<GoogleSignInAccount>) = viewModelScope.launch {
        googleSignInIsInit = false
        val result = authRepository.googleLogin(task)
        _loginFlow.value = result
    }

    fun register(name: String, email: String, password: String) = viewModelScope.launch {
        _registerFlow.value = Resource.Loading
        val result = authRepository.register(name, email, password)
        _registerFlow.value = result
    }

    fun logout() {
        authRepository.logout()
        GoogleSignIn.getClient(context, gso).signOut()
        _loginFlow.value = null
        _registerFlow.value = null
    }
}

