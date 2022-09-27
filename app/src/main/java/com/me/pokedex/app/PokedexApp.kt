package com.me.pokedex.app

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.me.pokedex.MainActivity
import com.me.pokedex.R
import com.me.pokedex.ui.LoadingScreen
import com.me.pokedex.ui.PokedexRouter
import com.me.pokedex.ui.Screen
import com.me.pokedex.ui.theme.MainScreen
import kotlinx.coroutines.delay

private const val TAG = "PokedexApp"

@Composable
fun PokedexApp() {
    Surface(color = MaterialTheme.colors.background) {
        Crossfade(targetState = PokedexRouter.currentScreen) { screenState ->
            Log.d(TAG, "cambio de screen")
            when (screenState.value) {
                is Screen.LoadingDataScreen -> {
                    LoadingScreen { showToast() }
                }
                is Screen.MainScreen -> MainScreen {
                    TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
                }
                is Screen.PokemonDetailsView -> MainScreen {
                    TopAppBar(title = { Text(text = stringResource(R.string.pokemon_details_title)) })
                }
            }
        }
    }
}

private suspend fun showToast() {
    delay(200)
    Toast.makeText(MainActivity.context, "Refreshing Data", Toast.LENGTH_SHORT).show()
}