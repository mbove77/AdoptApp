package com.bove.martin.adoptapp.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bove.martin.adoptapp.R
import com.bove.martin.adoptapp.presentation.login.AuthViewModel
import com.bove.martin.adoptapp.presentation.navigation.Screen
import com.bove.martin.adoptapp.presentation.theme.AdoptAppTheme

/**
 * Created by Martín Bove on 24/11/2022.
 * E-mail: mbove77@gmail.com
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: AuthViewModel?, navController: NavHostController) {
    Scaffold(
        content = { Content() } ,
        topBar = { Toolbar(onLogoutButtonClic = {
            viewModel?.logout()
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }) }
    )
    

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(onLogoutButtonClic: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
           IconButton(onClick = onLogoutButtonClic) {
               Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Logout")
           }

        }
    )
}

@Composable
fun Content() {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Home Screen")
    }
}

@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    AdoptAppTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            HomeScreen(null, rememberNavController())
        }
    }
}

