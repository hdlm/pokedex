package com.me.pokedex.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.me.pokedex.R
import com.me.pokedex.commons.emptyComposable
import com.me.pokedex.commons.loadPicture
import com.me.pokedex.commons.topBarFun
import com.me.pokedex.presentation.MainViewModel
import com.me.pokedex.presentation.domain.Pokemon
import org.koin.androidx.compose.getViewModel

const val CANT_IMG = 20
const val DEFAULT_IMAGE = R.mipmap.ic_empty
const val DEFAULT_IMAGE_URL = "https://api.chucknorris.io/img/chucknorris_logo_coloured_small@2x.png"

@Composable
fun MainScreen(
    viewModel: MainViewModel = getViewModel(),
    actionBarFun: topBarFun = { emptyComposable() },
) {
    val showLoading = remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            actionBarFun (selectedIndex.value)
        },
    ) {
        when (selectedIndex.value) {
            0 -> { showLoading.value = true }
//            1 -> { PokemonList(viewModel) }
        }
    }
}
/*

@Composable
fun PokemonList(viewModel: MainViewModel) {
    LazyColumn {
        items(items) { item -> ListItem(item) }
    }
}

@Composable
fun ListItem(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = pokemon.nombre,
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = modifier.height(8.dp))
    }
}

@Composable
fun ListPictures(imgUrl: String) {
    LazyRow {
        val image = loadPicture(url = imgUrl , defaultImage = DEFAULT_IMAGE)
        if (image.value != null) {
            Image(
                bitmap = image.value!!.asImageBitmap(),
                contentDescription = ""
            )
        }
    }

}*/
