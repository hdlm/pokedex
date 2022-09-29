package com.me.pokedex.presentation.usecase

import com.me.pokedex.commons.CoroutineHelper
import com.me.pokedex.commons.onDismissComposableType
import com.me.pokedex.data.repository.PokemonLocalRepository
import com.me.pokedex.data.repository.PokemonRepository
import com.me.pokedex.presentation.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.inject

interface FetchPokemonsDataUseCase  {

    val repository: PokemonRepository
    val localRepository: PokemonLocalRepository

    suspend operator fun invoke()
    suspend fun call(helperCoroutine : CoroutineHelper)
    fun getPokemons(): Flow<List<Pokemon>>
    suspend fun cleanData()


}
