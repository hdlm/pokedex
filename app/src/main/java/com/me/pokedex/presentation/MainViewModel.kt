package com.me.pokedex.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.me.pokedex.commons.CoroutineHelper
import com.me.pokedex.networking.model.PokemonDto
import com.me.pokedex.presentation.domain.Pokemon
import com.me.pokedex.presentation.usecase.FetchPokemonsDataUseCase
import contextProvider.CoroutineContextProvider
import contextProvider.CoroutineContextProviderImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope

class MainViewModel(
    private val fetchPokemonsDataUseCase : FetchPokemonsDataUseCase,
) : KoinViewModel() {
    val flowOfpokemons = fetchPokemonsDataUseCase.getPokemons().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5000)),
        initialValue = emptyList()
    )

    val pokemons : List<Pokemon>
        get() = fetchPokemonsDataUseCase.repository.pokemons

    fun getFreshData() {
        Log.d(TAG, "getFreshData()")
        viewModelScope.launch {
            fetchPokemonsDataUseCase.invoke()
        }
    }

    companion object {
        private const val TAG = "munky.MainViewModel"
    }
}

