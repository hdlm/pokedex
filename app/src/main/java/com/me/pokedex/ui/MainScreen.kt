package com.me.pokedex.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.me.pokedex.commons.emptyComposable
import com.me.pokedex.commons.topBarFun

@Composable
fun MainScreen(navController : NavController, actionBarFun: topBarFun = { emptyComposable() }) {

    val showReloadButton = remember { mutableStateOf(true) }
    val selectedIndex = remember { mutableStateOf(0) }

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
                            //TODO reload data from Database
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
    }

}