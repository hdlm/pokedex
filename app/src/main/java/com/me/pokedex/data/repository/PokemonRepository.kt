package com.me.pokedex.data.repository

import com.me.pokedex.commons.CoroutineHelper
import com.me.pokedex.commons.callbackFlowApi
import com.me.pokedex.presentation.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.inject

interface PokemonRepository {

    val pokemons : MutableList<Pokemon>

    suspend fun fetchPokemons()

}