package com.bove.martin.adoptapp.presentation.login

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bove.martin.adoptapp.R
import com.bove.martin.adoptapp.presentation.components.DogAnimation
import com.bove.martin.adoptapp.presentation.components.GoogleButton
import com.bove.martin.adoptapp.presentation.components.PassTextFieldComp
import com.bove.martin.adoptapp.presentation.components.TextFieldComp
import com.bove.martin.adoptapp.presentation.theme.AdoptAppTheme
import com.bove.martin.adoptapp.presentation.theme.extraLargeSpace
import com.bove.martin.adoptapp.presentation.theme.largeSpace
import com.bove.martin.adoptapp.presentation.theme.normalSpace


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdoptAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(extraLargeSpace, normalSpace)
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        DogAnimation()
        EmailLoginText()
        Spacer(modifier = Modifier.height(normalSpace))
        PassLoginText()
        Spacer(modifier = Modifier.height(normalSpace))
        LoginButton()
        Spacer(modifier = Modifier.height(largeSpace))
        OrSeparator()
        Spacer(modifier = Modifier.height(largeSpace))
        GoogleButton(
            Modifier.fillMaxWidth(),
            text = stringResource(R.string.google_btn),
            loadingText = stringResource(R.string.google_btn_loading),
            onClicked = {}
        )
        Spacer(modifier = Modifier.height(extraLargeSpace))
        RegisterLink()
    }
}


@Composable
fun OrSeparator() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .height(1.dp)
            .weight(1f)
            .background(color = Gray)) {}
        ClickableText(
            text = AnnotatedString(stringResource(R.string.also_separator)), onClick = {}, modifier = Modifier.weight(1f),
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
fun EmailLoginText() {
    var emailText by rememberSaveable { mutableStateOf("") }
    TextFieldComp(value = emailText,
        onValueChange = { emailText = it },
        placeholder = stringResource(R.string.email_placeholder),
        icon = painterResource(R.drawable.email),
        label = stringResource(R.string.email_label),
        keyboardType = KeyboardType.Email)
}

@Composable
fun PassLoginText() {
    var passText by rememberSaveable { mutableStateOf("") }

    PassTextFieldComp(value = passText,
        onValueChange = { passText = it },
        placeholder = stringResource(R.string.password_placeholder),
        icon = painterResource(id = R.drawable.pass_icon),
        label = stringResource(R.string.password_label))
}

@Composable
fun RegisterLink() {
    TextButton(onClick = { }) {
        Text(textAlign = TextAlign.Center,
            text = stringResource(id = R.string.login_link))
    }
}

@Composable
fun LoginButton() {
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = { })
    {
        Text(text = stringResource(R.string.login_btn))
    }
}

@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview2() {
    AdoptAppTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            LoginScreen()
        }
    }
}