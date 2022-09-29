package com.me.pokedex.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.me.pokedex.ui.theme.TextColorVariant
import com.me.pokedex.R
import com.me.pokedex.commons.loadPicture
import com.me.pokedex.ui.theme.typography

private val TAG  = "PokemonCard"
const val DEFAULT_IMAGE = R.mipmap.ic_x_marks

@Composable
fun PokemonCard(
    id: String,
    nombre: String,
    imgUrl: String,
) {

    Log.d(TAG, "PokemonCard -> nombre: ${nombre}, id: ${id}")
    Box(
        modifier = Modifier.fillMaxSize()
            .height(120.dp)
            .background(TextColorVariant)
            .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val image = loadPicture(url = imgUrl, defaultImage = DEFAULT_IMAGE )
                if (image.value != null) {
                    Image(
                        bitmap = image.value!!.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(96.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Color.DarkGray, RoundedCornerShape(8.dp))
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = nombre,
                    style = typography.h2
                )

            }
        }
    }
}