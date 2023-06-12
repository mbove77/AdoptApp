package com.bove.martin.adoptapp.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bove.martin.adoptapp.data.Resource
import com.bove.martin.adoptapp.domain.usecases.EmailLoginUseCase
import com.bove.martin.adoptapp.domain.usecases.FinishGoogleLoginUseCase
import com.bove.martin.adoptapp.domain.usecases.GetCurrentUserUseCase
import com.bove.martin.adoptapp.domain.usecases.LogOutUseCase
import com.bove.martin.adoptapp.domain.usecases.RegisterUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val emailLoginUseCase: EmailLoginUseCase,
    private val finishGoogleLoginUseCase: FinishGoogleLoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logOutUseCase: LogOutUseCase,
    getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _registerFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val registerFlow: StateFlow<Resource<FirebaseUser>?> = _registerFlow


    // TODO Ver como se puede manejar el problema de la llamada doble a Google, es por la re-compoiscion del boton aparentemente.
    private var googleSignInIsInit = false
    private val currentUser = getCurrentUserUseCase()

    init {
        Log.d("AuthViewModel", "init()")
        if (currentUser != null) {
            _loginFlow.value = Resource.Success(currentUser)
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        _loginFlow.value = emailLoginUseCase(email, password)
    }

    fun startGoogleLogin() {
        Log.d("AuthViewModel", "startGoogleLogin()")
        if (!googleSignInIsInit) {
            googleSignInIsInit = true
            _loginFlow.value = Resource.Loading
            _loginFlow.value = Resource.StartGoogleLogin

        }
    }

    fun finishGoogleLogin(task: Task<GoogleSignInAccount>) = viewModelScope.launch {
        Log.d("AuthViewModel", "finishGoogleLogin()")
        googleSignInIsInit = false
        _loginFlow.value = finishGoogleLoginUseCase(task)
    }

    fun register(name: String, email: String, password: String) = viewModelScope.launch {
        _registerFlow.value = Resource.Loading
        _registerFlow.value = registerUseCase(name, email, password)
    }

    fun logout() {
        logOutUseCase()
        _loginFlow.value = null
        _registerFlow.value = null
    }
}

