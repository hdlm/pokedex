package com.me.pokedex

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.me.pokedex.di.Modules.appModule
import com.me.pokedex.presentation.MainViewModel
import com.me.pokedex.router.NavigationRouter
import com.me.pokedex.ui.theme.PokedexTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext


class MainActivity : ComponentActivity(), KoinComponent {
    private val mainViewModel by inject<MainViewModel>()


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        GlobalContext.startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }


        setContent {

            PokedexTheme {

                val activity = LocalView.current.context as Activity
                val backgroundArgb = MaterialTheme.colors.secondary.toArgb()
                activity.window.statusBarColor = backgroundArgb

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
