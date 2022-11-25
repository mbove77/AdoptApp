package com.bove.martin.adoptapp.presentation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bove.martin.adoptapp.R
import com.bove.martin.adoptapp.presentation.navigation.Screen
import com.bove.martin.adoptapp.presentation.theme.AdoptAppTheme
import com.bove.martin.adoptapp.presentation.theme.Purple800
import kotlinx.coroutines.delay

@Composable
fun SplashAnimation(navController: NavHostController) {
    val animationDuration = 2500L
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
       delay(10)
       startAnimation = true
    }

    LaunchedEffect(Unit) {
        delay(animationDuration)
        navController.popBackStack()
        navController.navigate(Screen.Login.route)
    }

    Splash(startAnimation, animationDuration.toInt())
}

@Composable
fun Splash(startAnimation: Boolean, animationDuration: Int) {
    Box(Modifier
        .fillMaxSize()
        .background(Purple800),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = startAnimation,
            exit = fadeOut(animationSpec = tween(animationDuration, 0, FastOutSlowInEasing)),
            enter = fadeIn(animationSpec = tween(animationDuration, 0, FastOutSlowInEasing)),
        ) {
            Logo()
        }
    }
}

@Composable
fun Logo() {
    Icon(painter = painterResource(id = R.drawable.logo),
        contentDescription = "App Logo",
        modifier = Modifier
            .size(250.dp),
        tint = Color.Unspecified
    )
}

@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplahsPreview() {
    AdoptAppTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            Splash(true, animationDuration = 2500)
        }
    }
}