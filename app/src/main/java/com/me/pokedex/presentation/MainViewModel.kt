package com.me.pokedex.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.me.pokedex.commons.onDismissComposableType
import com.me.pokedex.presentation.usecase.FetchPokemonsDataUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val fetchPokemonsDataUseCase : FetchPokemonsDataUseCase,
) : KoinViewModel() {
    val flowOfpokemons = fetchPokemonsDataUseCase.getPokemons().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5000)),
        initialValue = emptyList()
    )

    fun getFreshData() {
        Log.d(TAG, "getFreshData()")
        viewModelScope.launch {
            fetchPokemonsDataUseCase.invoke()
        }
    }

    fun cleanData() {
        Log.d(TAG, "cleanData()")
        viewModelScope.launch {
            fetchPokemonsDataUseCase.cleanData()
        }
    }

    companion object {
        private const val TAG = "munky.MainViewModel"
    }
}

