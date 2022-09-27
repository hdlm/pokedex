package com.me.pokedex.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.me.pokedex.presentation.domain.Pokemon
import com.me.pokedex.ui.theme.TextColorVariant

@Composable
fun PokemonCard(poke: Pokemon) {
    Box(
        modifier = Modifier.fillMaxSize()
            .height(120.dp)
            .background(TextColorVariant)
            .padding(8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //TODO add content
        }
    }
}