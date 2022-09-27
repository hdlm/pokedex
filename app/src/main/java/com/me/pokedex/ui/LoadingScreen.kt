package com.me.pokedex.ui

import android.annotation.SuppressLint
import android.util.Log

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.me.pokedex.commons.onDismissTypeSuspend
import com.me.pokedex.presentation.MainViewModel
import com.me.pokedex.presentation.domain.Pokemon
import kotlinx.coroutines.*
import org.koin.androidx.compose.get

private const val TAG = "munky.LoadingScreen"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadingScreen(
    viewModel: MainViewModel = get(),
    onScreenLoaded: onDismissTypeSuspend
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val mainScreenScope = rememberCoroutineScope()
    Log.d(TAG, "LoadingScreen compose function")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(18.dp))
        Text("Loading Pokemons...")
        viewModel.getFreshData()
        mainScreenScope.launch {
            onScreenLoaded()
        }
        Spacer(Modifier.height(30.dp))
    }
    val uiStateFlow = remember(viewModel.flowOfpokemons, lifecycleOwner) {
        viewModel.flowOfpokemons.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val pokemons by uiStateFlow.collectAsState(emptyList())
    PokemonList(pokemons = pokemons)
}

@Composable
private fun PokemonList(pokemons: List<Pokemon>) {
    Text(text = "List size: ${pokemons.count()}")
//    LazyColumn(contentPadding = PaddingValues(16.dp)) {
//        pokemons.size?.let { item ->
//            items(item) { index ->
//                pokemons[index].nombre?.let { it1 -> Text(text = it1) }
//            }
//        }
//    }
}
