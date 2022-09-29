package com.me.pokedex.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import com.me.pokedex.R
import com.me.pokedex.commons.emptyComposable
import com.me.pokedex.commons.onDismissComposableType
import com.me.pokedex.commons.onDismissTypeSuspend
import com.me.pokedex.commons.topBarFun
import com.me.pokedex.presentation.MainViewModel
import com.me.pokedex.presentation.domain.Pokemon
import kotlinx.coroutines.*
import org.koin.androidx.compose.get

private const val TAG = "munky.MainScreen"

@Composable
fun MainScreen(
    navController : NavController,
    viewModel: MainViewModel = get(),
    actionBarFun: topBarFun = { emptyComposable() }
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val mainScreenScope = rememberCoroutineScope()
    val showReloadButton = remember { mutableStateOf(true) }
    val selectedIndex = remember { mutableStateOf(0) }

    val onDeletedData : onDismissTypeSuspend = {
        showReloadButton.value = false
        delay(5000)
        showReloadButton.value = true
    }

    Scaffold(
        topBar = {
            actionBarFun(selectedIndex.value)
        },
        floatingActionButton = {
            if (selectedIndex.value == 0) {
                if (showReloadButton.value) {
                    FloatingActionButton(
                        modifier = Modifier
                            .padding(16.dp),
                        onClick = {
                            showReloadButton.value = false
                            viewModel.cleanData()
                            GlobalScope.launch {
                                onDeletedData.invoke()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null
                        )
                    }
                }
            }
        },
    ) {
//        when (selectedIndex.value) {
//            0 -> navController.navigate(route = "main_screen")
//            1 -> navController.navigate(route = "main_screen")
//        }
        val uiStateFlow = remember(viewModel.flowOfpokemons, lifecycleOwner) {
            viewModel.flowOfpokemons.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        }
        val pokemons by uiStateFlow.collectAsState(initial = emptyList())
        ListItem(items = pokemons)
    }
}

@Composable
private fun ListItem(items: List<Pokemon>) {

    val extractUrl: ( (List<String>) -> String ) =  { items ->
        if(!items.isEmpty()) items[0]
        else ""
    }
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(items) { item ->
            PokemonCard(
                id = item.id!!,
                nombre = item.nombre!!,
                imgUrl = extractUrl.invoke(item.imgUrl!!)
            )
        }
    }

}