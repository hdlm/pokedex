package com.me.pokedex

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.res.stringResource
import com.me.pokedex.app.PokedexApp
import com.me.pokedex.data.repository.PokemonRepository
import com.me.pokedex.di.Modules.appModule
import com.me.pokedex.presentation.MainViewModel
import com.me.pokedex.ui.theme.PokedexTheme
import com.me.pokedex.ui.theme.MainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        GlobalContext.startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            PokedexTheme {
                PokedexApp()
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TAG = "MainActivity"
    }
}
