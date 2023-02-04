package com.merttoptas.diaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.merttoptas.diaryapp.core.data.util.NetworkMonitor
import com.merttoptas.diaryapp.features.diary.DiaryApp
import com.merttoptas.diaryapp.ui.theme.DiaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DiaryAppTheme {
                DiaryApp(networkMonitor = networkMonitor)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiaryAppTheme {
    }
}