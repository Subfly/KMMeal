package dev.subfly.kmmeal.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.subfly.kmmeal.android.navigation.KMMNavHost
import dev.subfly.kmmeal.android.common.theme.AppTheme
import dev.subfly.kmmeal.state.liked.LikedMealsStateMachine
import org.koin.androidx.compose.koinViewModel
import org.koin.core.component.KoinComponent

val LocalGlobalNavigator = compositionLocalOf<NavHostController> {
    error("No nav host found!")
}
val LocalLikedMealsProvider = compositionLocalOf<LikedMealsStateMachine> {
    error("No liked meals state machine found!")
}

class MainActivity : ComponentActivity(), KoinComponent {
    private lateinit var globalNavController: NavHostController
    private lateinit var likedMealsStateMachine: LikedMealsStateMachine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            globalNavController = rememberNavController()
            likedMealsStateMachine = koinViewModel()

            AppTheme {
                CompositionLocalProvider(
                    LocalGlobalNavigator provides globalNavController,
                    LocalLikedMealsProvider provides likedMealsStateMachine
                ) {
                    KMMNavHost()
                }
            }
        }
    }
}
