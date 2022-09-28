package com.me.pokedex

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.me.pokedex.di.Modules.appModule
import com.me.pokedex.ui.NavigationScreen
import com.me.pokedex.ui.theme.PokedexTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext

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
                NavigationScreen()
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TAG = "MainActivity"
    }
}
