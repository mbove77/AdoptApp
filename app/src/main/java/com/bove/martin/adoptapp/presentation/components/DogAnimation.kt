package com.bove.martin.adoptapp.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.bove.martin.adoptapp.R

/**
 * Created by Martín Bove on 24/11/2022.
 * E-mail: mbove77@gmail.com
 */
@Composable
fun DogAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.dog_icon))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        speed = 0.5f,
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.FillWidth
    )
}