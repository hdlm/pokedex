package com.me.pokedex.networking

import com.me.pokedex.networking.model.PokemonDetailsDto
import com.me.pokedex.networking.model.PokemonDto
import com.me.pokedex.networking.model.PokemonSpecieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokedexApiService {
    @GET
    suspend fun fetchPokemons(@Url url : String) : Response<PokemonDto>
    @GET
    suspend fun fetchPokemon(@Url url : String) : Response<PokemonDetailsDto>
    @GET
    suspend fun getPokemonSpecie(@Url url : String) : Response<PokemonSpecieDto>
}