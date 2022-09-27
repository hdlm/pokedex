package com.me.pokedex.networking

import android.util.Log
import com.me.pokedex.networking.model.PokemonDetailsDto
import com.me.pokedex.networking.model.PokemonDto
import com.me.pokedex.networking.model.PokemonResultDto
import com.me.pokedex.networking.model.PokemonSpecieDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class PokedexApiAdapter : ApiAdapter {

    override val CANTIDAD: Int = 2

    override suspend fun fetchPokemons(): List<PokemonResultDto>? {
        val url = "${BASE_URL}${PARAMS[Option.POKEMON.ordinal]}?limit=${CANTIDAD}"
        Log.d(TAG, "fetchPokemons.url =  $url")
        val call: Response<PokemonDto> = PokedexApiAdapter.getRetrofit().create(PokedexApiService::class.java).fetchPokemons(url)
        if (!call.isSuccessful) {
            Log.d(TAG, "fetchPokemons called Failure")
            return null
        }
        Log.d(TAG, "fetchPokemons called Success")
        return call.body()!!.results
    }

    override suspend fun fetchPokemon(url: String): PokemonDetailsDto? {
        val call : Response<PokemonDetailsDto> = PokedexApiAdapter.getRetrofit().create(PokedexApiService::class.java).fetchPokemon(url)
        if (!call.isSuccessful) {
            Log.d(TAG, "fetchPokemon called Failure")
            return null
        }
        Log.d(TAG, "fetchPokemon called Success")
        return call.body()!!
    }
    override suspend fun fetchPokemon(id: Int) : PokemonDetailsDto? {
        val url = "${BASE_URL}${PARAMS[Option.DETAIL.ordinal]}/${id}/"
        val call : Response<PokemonDetailsDto> = PokedexApiAdapter.getRetrofit().create(PokedexApiService::class.java).fetchPokemon(url)
        if (!call.isSuccessful) {
            return null
        }
        return call.body()
    }

    override suspend fun selectPokemonSpecie(url: String) : PokemonSpecieDto? {
        val call : Response<PokemonSpecieDto> = PokedexApiAdapter.getRetrofit().create(PokedexApiService::class.java).getPokemonSpecie(url)
        if (!call.isSuccessful) {
            return null
        }
        return call.body()
    }
    override suspend fun selectPokemonSpecie(id: Int) : PokemonSpecieDto? {
        val url = "${BASE_URL}${PARAMS[Option.SPECIE.ordinal]}/${id}/"
        val call : Response<PokemonSpecieDto> = PokedexApiAdapter.getRetrofit().create(PokedexApiService::class.java).getPokemonSpecie(url)
        if (!call.isSuccessful) {
            return null
        }
        return call.body()
    }



    companion object {
        private const val TAG = "munky.PokedexApiAdapter"
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        private val PARAMS = listOf(
            "pokemon/",
            "pokemon/",
            "pokemon-species/"
        )
        private enum class Option {
            POKEMON, DETAIL, SPECIE,
        }

        fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}