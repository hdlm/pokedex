package com.me.pokedex

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.me.pokedex.di.Modules.appModule
import com.me.pokedex.presentation.MainViewModel
import com.me.pokedex.router.NavigationRouter
import com.me.pokedex.ui.theme.PokedexTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {
    private val mainViewModel by inject<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        GlobalContext.startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            PokedexTheme {
                NavigationRouter()
                mainViewModel.getFreshData()
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TAG = "MainActivity"
    }
}
