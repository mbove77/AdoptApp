package com.bove.martin.adoptapp.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.bove.martin.adoptapp.R
import com.bove.martin.adoptapp.presentation.components.DogAnimation
import com.bove.martin.adoptapp.presentation.components.PassTextFieldComp
import com.bove.martin.adoptapp.presentation.components.TextFieldComp
import com.bove.martin.adoptapp.presentation.theme.AdoptAppTheme
import com.bove.martin.adoptapp.presentation.theme.extraLargeSpace
import com.bove.martin.adoptapp.presentation.theme.largeSpace
import com.bove.martin.adoptapp.presentation.theme.normalSpace

@Composable
fun RegisterScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(extraLargeSpace, normalSpace)
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        DogAnimation()
        NameRegisterText()
        Spacer(modifier = Modifier.height(normalSpace))
        EmailRegisterText()
        Spacer(modifier = Modifier.height(normalSpace))
        PassRegisterText()
        Spacer(modifier = Modifier.height(normalSpace))
        RePassRegisterText()
        Spacer(modifier = Modifier.height(largeSpace))
        RegisterButton()
        Spacer(modifier = Modifier.height(extraLargeSpace))
        LoginLink()
    }
}

@Composable
fun LoginLink() {
    TextButton(onClick = { }) {
        Text(textAlign = TextAlign.Center,
            text = stringResource(id = R.string.register_link))
    }
}

@Composable
fun RegisterButton() {
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = { })
    {
        Text(text = stringResource(R.string.register_btn))
    }
}

@Composable
fun NameRegisterText() {
    var nameText by rememberSaveable { mutableStateOf("") }
    TextFieldComp(value = nameText,
        onValueChange = { nameText = it },
        placeholder = stringResource(R.string.name_placeholder),
        icon = painterResource(id = R.drawable.person),
        label = stringResource(R.string.name_label),
        keyboardType = KeyboardType.Text)
}


@Composable
fun EmailRegisterText() {
    var emailText by rememberSaveable { mutableStateOf("") }
    TextFieldComp(value = emailText,
        onValueChange = { emailText = it },
        placeholder = stringResource(R.string.email_placeholder),
        icon = painterResource(R.drawable.email),
        label = stringResource(R.string.email_label),
        keyboardType = KeyboardType.Email)
}

@Composable
fun PassRegisterText() {
    var passText by rememberSaveable { mutableStateOf("") }

    PassTextFieldComp(value = passText,
        onValueChange = { passText = it },
        placeholder = stringResource(R.string.password_placeholder),
        icon = painterResource(id = R.drawable.pass_icon),
        label = stringResource(R.string.password_label))
}

@Composable
fun RePassRegisterText() {
    var rePassText by rememberSaveable { mutableStateOf("") }

    PassTextFieldComp(value = rePassText,
        onValueChange = { rePassText = it },
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
//            RegisterScreen()
        }
    }
}