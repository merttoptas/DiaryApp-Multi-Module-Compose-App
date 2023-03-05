package com.merttoptas.diaryapp.features.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.merttoptas.diaryapp.R

/**
 * Created by mertcantoptas on 05.03.2023
 */
@Composable
fun DiaryLoadingAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            LottieAnimation(
                composition,
                modifier = Modifier.size(100.dp),
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}