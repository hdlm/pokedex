package com.me.pokedex.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.me.pokedex.networking.ApiStatus

sealed class Screen (val title: String) {
    object SplashScreen : Screen("Loading data...")
    object MainScreen : Screen("Pokedex")
    object PokemonDetailsView : Screen ("Details")
}

sealed class DestinationScreen(val route: String) {
    object SplashScreenDest : DestinationScreen(route =
    "splash_screen")
    object MainScreenDest : DestinationScreen(route = "main_screen")
}

object PokedexRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SplashScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }

    fun changeStatus(status: ApiStatus) {
        when  (status) {
            ApiStatus.LOADING -> if (currentScreen.value != Screen.SplashScreen) navigateTo(Screen.SplashScreen)
            ApiStatus.SUCCESS -> navigateTo(Screen.MainScreen)
        }
    }
}