package com.bove.martin.adoptapp.presentation.login

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bove.martin.adoptapp.R
import com.bove.martin.adoptapp.data.Resource
import com.bove.martin.adoptapp.presentation.components.DogAnimation
import com.bove.martin.adoptapp.presentation.components.GoogleButton
import com.bove.martin.adoptapp.presentation.components.PassTextFieldComp
import com.bove.martin.adoptapp.presentation.components.TextFieldComp
import com.bove.martin.adoptapp.presentation.navigation.Screen
import com.bove.martin.adoptapp.presentation.theme.AdoptAppTheme
import com.bove.martin.adoptapp.presentation.theme.extraLargeSpace
import com.bove.martin.adoptapp.presentation.theme.largeSpace
import com.bove.martin.adoptapp.presentation.theme.normalSpace

@Composable
fun LoginScreen(viewModel: AuthViewModel?, navController: NavHostController) {
    var emailText by rememberSaveable { mutableStateOf("") }
    var passText by rememberSaveable { mutableStateOf("") }

    var isLoading by rememberSaveable { mutableStateOf(false) }
    val loginFlow = viewModel?.loginFlow?.collectAsState()

    val activity = LocalContext.current.findActivity()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(extraLargeSpace, normalSpace)
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (!isLoading) {
            DogAnimation()
            EmailLoginText(emailText, onValueChange = {
                emailText = it
            })
            Spacer(modifier = Modifier.height(normalSpace))
            PassLoginText(passText, onValueChange = {
                passText = it
            })
            Spacer(modifier = Modifier.height(normalSpace))
            LoginButton(onClicked = {
                viewModel?.login(emailText, passText)
            })
            Spacer(modifier = Modifier.height(largeSpace))
            OrSeparator()
            Spacer(modifier = Modifier.height(largeSpace))
            GoogleButton(
                Modifier.fillMaxWidth(),
                text = stringResource(R.string.google_btn),
                loadingText = stringResource(R.string.google_btn_loading),
                onClicked = {
                    viewModel?.googleLogin(activity)
                }
            )
            Spacer(modifier = Modifier.height(extraLargeSpace))
            RegisterLink(onClicked = {
                navController.navigate(Screen.Register.route)
            })
        } else {
            CircularProgressIndicator()
        }
    }

    loginFlow?.value.let {
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

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

@Composable
fun OrSeparator() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .height(1.dp)
            .weight(1f)
            .background(color = Gray)) {}
        ClickableText(
            text = AnnotatedString(stringResource(R.string.also_separator)),
            onClick = {},
            modifier = Modifier.weight(1f),
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            ),
        )
        Box(modifier = Modifier
            .height(1.dp)
            .weight(1f)
            .background(color = Gray)) {}
    }
}

@Composable
fun EmailLoginText(text: String, onValueChange: (String) -> Unit) {
    TextFieldComp(value = text,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.email_placeholder),
        icon = painterResource(R.drawable.email),
        label = stringResource(R.string.email_label),
        keyboardType = KeyboardType.Email)
}

@Composable
fun PassLoginText(text: String, onValueChange: (String) -> Unit) {
    PassTextFieldComp(value = text,
        onValueChange = onValueChange,
        placeholder = stringResource(R.string.password_placeholder),
        icon = painterResource(id = R.drawable.pass_icon),
        label = stringResource(R.string.password_label))
}

@Composable
fun RegisterLink(onClicked: () -> Unit) {
    TextButton(onClick = onClicked) {
        Text(textAlign = TextAlign.Center,
            text = stringResource(id = R.string.login_link))
    }
}

@Composable
fun LoginButton(onClicked: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = onClicked)
    {
        Text(text = stringResource(R.string.login_btn))
    }
}

@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginPreview() {
    AdoptAppTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            LoginScreen(null, rememberNavController())
        }
    }
}