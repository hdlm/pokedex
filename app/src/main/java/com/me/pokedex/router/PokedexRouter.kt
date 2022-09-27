package com.me.pokedex.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.me.pokedex.networking.ApiStatus

sealed class Screen (val title: String) {
    object LoadingDataScreen : Screen("Loading data...")
    object MainScreen : Screen("Pokedex")
    object PokemonDetailsView : Screen ("Details")
}

object PokedexRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoadingDataScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }

    fun changeStatus(status: ApiStatus) {
        when  (status) {
            ApiStatus.LOADING -> if (currentScreen.value != Screen.LoadingDataScreen) navigateTo(Screen.LoadingDataScreen)
            ApiStatus.SUCCESS -> navigateTo(Screen.MainScreen)
        }
    }
}