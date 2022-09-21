package com.me.pokedex

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.me.pokedex.di.Modules.appModule
import com.me.pokedex.ui.theme.PokedexTheme
import com.me.pokedex.ui.theme.ShowMainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        GlobalContext.startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            PokedexTheme {
                Log.d(TAG, "calling ShowMainScreen")
                ShowMainScreen()
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @JvmStatic
        val TAG = "MainActivity"
    }
}
