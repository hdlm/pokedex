package com.me.pokedex.networking

import com.me.pokedex.networking.model.PokemonDetailsDto
import com.me.pokedex.networking.model.PokemonResultDto
import com.me.pokedex.networking.model.PokemonSpecieDto

interface ApiAdapter {
    /**
     * El metodo es responsable de obtener N cantidad de pokemons
     */
    val CANTIDAD : Int
    suspend fun fetchPokemons(): List<PokemonResultDto>?
    suspend fun fetchPokemon(url: String) : PokemonDetailsDto?
    suspend fun fetchPokemon(id: Int) : PokemonDetailsDto?
    suspend fun selectPokemonSpecie(url: String) : PokemonSpecieDto?
    suspend fun selectPokemonSpecie(id: Int) : PokemonSpecieDto?

}

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING,
}

sealed class ApiResult <out T> (val status: ApiStatus, val data: T?, val message:String?) {

    data class Success<out R>(val _data: R?): ApiResult<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String): ApiResult<Nothing>(
        status = ApiStatus.ERROR,
        data = null,
        message = exception
    )

    data class Loading<out R>(val _data: R?): ApiResult<R>(
        status = ApiStatus.LOADING,
        data = _data,
        message = null
    )
}