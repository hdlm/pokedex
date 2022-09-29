package com.me.pokedex.router

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.me.pokedex.R
import com.me.pokedex.commons.MusicTheme
import com.me.pokedex.ui.MainScreen
import com.me.pokedex.ui.SplashScreen

sealed class DestinationScreen(val route: String) {
    object SplashScreenDest : DestinationScreen(route =
    "splash_screen")
    object MainScreenDest : DestinationScreen(route = "main_screen")
}


@Composable
fun NavigationRouter() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.SplashScreenDest.route
    ) {

        composable(route = DestinationScreen.SplashScreenDest.route) {
            SplashScreen(navController = navController)
            MusicTheme.playIntro()
        }

        composable(route = DestinationScreen.MainScreenDest.route) {
            MainScreen(navController = navController) {
                TopAppBar(title = {
                    when (it) {
                        0 -> Text(text = stringResource(id = R.string.app_name))
                        else -> Text(text = stringResource(id = R.string.app_name))
                    }
                } )
            }
        }
    }
}