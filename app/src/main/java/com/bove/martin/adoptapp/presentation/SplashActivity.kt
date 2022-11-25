package com.bove.martin.adoptapp.presentation

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.draw.alpha
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

// TODO Fix animation
@Composable
fun SplashAnimation(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            delayMillis = 3000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Login.route)
    }

    Splash(alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(Modifier
        .fillMaxSize()
        .background(Purple800),
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(250.dp)
                .alpha(alpha),
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplahsPreview() {
    AdoptAppTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            Splash(alpha = 1f)
        }
    }
}