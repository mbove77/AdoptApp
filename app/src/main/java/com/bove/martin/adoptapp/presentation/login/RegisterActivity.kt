package com.bove.martin.adoptapp.presentation.login

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bove.martin.adoptapp.R
import com.bove.martin.adoptapp.data.Resource
import com.bove.martin.adoptapp.presentation.components.DogAnimation
import com.bove.martin.adoptapp.presentation.components.PassTextFieldComp
import com.bove.martin.adoptapp.presentation.components.TextFieldComp
import com.bove.martin.adoptapp.presentation.navigation.Screen
import com.bove.martin.adoptapp.presentation.theme.AdoptAppTheme
import com.bove.martin.adoptapp.presentation.theme.extraLargeSpace
import com.bove.martin.adoptapp.presentation.theme.largeSpace
import com.bove.martin.adoptapp.presentation.theme.normalSpace

@Composable
fun RegisterScreen(viewModel: AuthViewModel?, navController: NavHostController) {
    var nameText by rememberSaveable { mutableStateOf("") }
    var emailText by rememberSaveable { mutableStateOf("") }
    var passText by rememberSaveable { mutableStateOf("") }
    var rePassText by rememberSaveable { mutableStateOf("") }

    var isLoading by rememberSaveable { mutableStateOf(false) }
    val registerFlow = viewModel?.registerFlow?.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(extraLargeSpace, normalSpace)
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        if (!isLoading) {
            DogAnimation()
            NameRegisterText(nameText, onValueChange = {
                nameText = it
            })
            Spacer(modifier = Modifier.height(normalSpace))
            EmailRegisterText(emailText, onValueChange = {
                emailText = it
            })
            Spacer(modifier = Modifier.height(normalSpace))
            PassRegisterText(passText, onValueChange = {
                passText = it
            })
            Spacer(modifier = Modifier.height(normalSpace))
            RePassRegisterText(rePassText, onValueChange = {
                rePassText = it
            })
            Spacer(modifier = Modifier.height(largeSpace))
            RegisterButton(onClicked = {
                viewModel?.register(nameText, emailText, passText)
            })
            Spacer(modifier = Modifier.height(extraLargeSpace))
            LoginLink(onClicked = {
                navController.navigate(Screen.Login.route)
            })
        } else {
            CircularProgressIndicator()
        }
    }

    registerFlow?.value.let {
        when (it) {
            is Resource.Failire -> {
                isLoading = false
                val context = LocalContext.current
                Toast.makeText(context,
                    stringResource(R.string.login_error),
                    Toast.LENGTH_LONG).show()
            }
            Resource.Loading -> {
                isLoading = true
            }
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
fun LoginLink(onClicked: () -> Unit) {
    TextButton(onClick = onClicked) {
        Text(textAlign = TextAlign.Center,
            text = stringResource(id = R.string.register_link))
    }
}

@Composable
fun RegisterButton(onClicked: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = onClicked)
    {
        Text(text = stringResource(R.string.register_btn))
    }
}

@Composable
fun NameRegisterText(text: String, onValueChange: (String) -> Unit) {
    TextFieldComp(value = text,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.name_placeholder),
        icon = painterResource(id = R.drawable.person),
        label = stringResource(R.string.name_label),
        keyboardType = KeyboardType.Text)
}


@Composable
fun EmailRegisterText(text: String, onValueChange: (String) -> Unit) {
    TextFieldComp(value = text,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.email_placeholder),
        icon = painterResource(R.drawable.email),
        label = stringResource(R.string.email_label),
        keyboardType = KeyboardType.Email)
}

@Composable
fun PassRegisterText(text: String, onValueChange: (String) -> Unit) {
    PassTextFieldComp(value = text,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.password_placeholder),
        icon = painterResource(id = R.drawable.pass_icon),
        label = stringResource(R.string.password_label))
}

@Composable
fun RePassRegisterText(text: String, onValueChange: (String) -> Unit) {
    PassTextFieldComp(value = text,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.confirm_password_placeholder),
        icon = painterResource(id = R.drawable.pass_icon),
        label = stringResource(R.string.confirm_password_label))
}


@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RegisterPreview() {
    AdoptAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
           RegisterScreen(null, rememberNavController())
        }
    }
}