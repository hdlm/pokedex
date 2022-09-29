package com.me.pokedex.data.repository

import android.util.Log
import com.me.pokedex.commons.CoroutineHelper
import com.me.pokedex.data.database.mapper.MapperPokemon.toPokemon
import com.me.pokedex.data.database.mapper.MapperPokemon.toPokemonDb
import com.me.pokedex.networking.ApiAdapter
import com.me.pokedex.networking.model.PokemonDetailsDto
import com.me.pokedex.networking.model.PokemonResultDto
import com.me.pokedex.presentation.domain.Pokemon
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class PokemonRepositoryImpl : PokemonRepository, KoinComponent {

    private val apiClient: ApiAdapter by inject()
    private val localRepository by inject<PokemonLocalRepository>()

    private val mPokemons: MutableList<Pokemon> = mutableListOf()
    override val pokemons: MutableList<Pokemon>
        get() = mPokemons

    override suspend fun fetchPokemons() {
        Log.d(TAG, "fetchPokemons()")
        val pokemons: List<PokemonResultDto>? = apiClient.fetchPokemons()
        Log.d(TAG, "fetchPokemons -> apiClient returned ${pokemons!!.count()} pokemons")

        pokemons?.let { items ->
            items.forEach { item ->
                var pokemonDetails : PokemonDetailsDto? = apiClient.fetchPokemon(item.url)
                Log.d(TAG, "fetchPokemons -> loaded from REST API, pokemon: ${pokemonDetails!!.name}")
                var pokemon : Pokemon = pokemonDetails.toPokemon(item.url)
                pokemon?.let {
                    mPokemons.add(pokemon)
                    Log.d(TAG, "fetchPokemons -> saving to local database, pokemon: ${pokemon.nombre}")
                    localRepository.savedToPokemon(
                        pokemon = pokemon.toPokemonDb(),
                        onDone = {}
                    )
                }
                Log.d(TAG, "fetchPokemons, saved ${mPokemons.count()} register(s) into database")
            }

        }
    }

    companion object {
        private const val TAG = "munky.PokemonRepositoryImpl"
    }
}