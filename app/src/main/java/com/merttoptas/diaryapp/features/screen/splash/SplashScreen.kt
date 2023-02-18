package com.merttoptas.diaryapp.features.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.merttoptas.diaryapp.R
import com.merttoptas.diaryapp.domain.state.ScreenState

/**
 * Created by mertcantoptas on 18.02.2023
 */

@Composable
fun SplashScreen(
    modifier: Modifier,
    screenState: ScreenState<SplashUiState>
) {

    Box(modifier = modifier.fillMaxSize()) {
        when (screenState) {
            is ScreenState.Loading -> Unit
            is ScreenState.Success -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                            contentDescription = null
                        )
                        Text(text = "Diary App", fontSize = 24.sp)
                    }

                }
            }
            is ScreenState.Error -> {
                //TODO: Handle error
            }
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        modifier = Modifier,
        screenState = ScreenState.Success(SplashUiState())
    )
}