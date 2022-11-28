package com.bove.martin.adoptapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bove.martin.adoptapp.AppConstants.Companion.GOOGLE_REQ_ID
import com.bove.martin.adoptapp.presentation.login.AuthViewModel
import com.bove.martin.adoptapp.presentation.navigation.AppNavHost
import com.bove.martin.adoptapp.presentation.theme.AdoptAppTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdoptAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    AppNavHost(viewModel)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_REQ_ID){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.finishGoogleLogin(task)
        }
    }
}
