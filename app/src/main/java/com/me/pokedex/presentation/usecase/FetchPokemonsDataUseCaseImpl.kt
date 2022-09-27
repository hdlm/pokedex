package com.me.pokedex.presentation.usecase

import android.util.Log
import com.me.pokedex.commons.CoroutineHelper
import com.me.pokedex.data.repository.PokemonLocalRepository
import com.me.pokedex.data.repository.PokemonRepository
import com.me.pokedex.presentation.domain.Pokemon
import contextProvider.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchPokemonsDataUseCaseImpl : FetchPokemonsDataUseCase,KoinComponent  {

    override val repository: PokemonRepository by inject()
    override val localRepository: PokemonLocalRepository by inject()

    suspend override operator fun invoke() =
        repository.fetchPokemons()

    suspend override fun call(helperCoroutine : CoroutineHelper) {
        Log.d(TAG, "call()")
        repository.fetchPokemons()
    }

    override fun getPokemons(): Flow<List<Pokemon>> =
        localRepository.getAllPokemon()

    companion object {
        private const val TAG = "munky.FetchPokemonsDataUseCase"
    }
}
