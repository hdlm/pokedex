package com.me.pokedex.networking

import com.me.pokedex.networking.model.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokedexApiService {
    @GET
    suspend fun getPokemons(@Url url : String) : Response<PokemonModel>
}