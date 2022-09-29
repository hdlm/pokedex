package com.me.pokedex.data.repository

import com.me.pokedex.commons.onDismissType
import com.me.pokedex.data.database.model.PokemonDbModel
import com.me.pokedex.presentation.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonLocalRepository {

    /**
     * Las funciones son responsables de salvar los pokemon en la base de datos
     */
    fun getAllPokemon(): Flow<List<Pokemon>>
    fun getPokemonById(pokemonId: String)
    suspend fun savedToPokemon(pokemon: PokemonDbModel, onDone: onDismissType)
}