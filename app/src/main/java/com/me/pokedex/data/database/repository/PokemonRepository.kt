package com.me.pokedex.data.database.repository

import com.me.pokedex.commons.onDismissType
import com.me.pokedex.data.database.model.PokemonDbModel
import com.me.pokedex.networking.model.PokemonModel

interface PokemonRepository {
    val pokemons : MutableList<PokemonModel>

    /**
     * Las funciones son responsables de salvar los pokemon en la base de datos
     */
    fun getAllPokemon()
    fun getPokemonById(pokemonId: String)
    fun savedToPokemon(pokemon: PokemonDbModel, onDone: onDismissType)
}