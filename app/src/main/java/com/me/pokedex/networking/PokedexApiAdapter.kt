package com.me.pokedex.networking

import com.me.pokedex.networking.model.PokemonModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokedexApiAdapter : ApiAdapter {
    override suspend fun selectPokes(cantidad: Int): PokemonModel? {
        val url = "${BASE_URL}${PARAMS}"
        val call : Response<PokemonModel> = PokedexApiAdapter.getRetrofit().create(PokedexApiService::class.java).getPokemons(url)
        if (!call.isSuccessful) {
            return null
        }
        return call.body()
    }

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        private const val PARAMS = "pokemon/"

        fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}