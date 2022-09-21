package com.me.pokedex.networking

import com.me.pokedex.networking.model.PokemonModel

interface ApiAdapter {
    /**
     * El metodo es responsable de obtener N cantidad de pokemons
     */
    suspend fun selectPokes(cantidad: Int) : PokemonModel?
}