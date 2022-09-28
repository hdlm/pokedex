package com.me.pokedex.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.me.pokedex.commons.MusicTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NavigationScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.SplashScreenDest.route
    ) {

        composable(route = DestinationScreen.SplashScreenDest.route) {
            SplashScreen(navController = navController)
            GlobalScope.launch(Dispatchers.Default) {
                MusicTheme.playIntro()
            }
        }

        composable(route = DestinationScreen.MainScreenDest.route) {
            MainScreen()
        }
    }
}