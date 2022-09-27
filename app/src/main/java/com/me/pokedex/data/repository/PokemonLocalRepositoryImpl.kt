package com.me.pokedex.data.repository

import android.util.Log
import com.me.pokedex.MainActivity
import com.me.pokedex.commons.onDismissType
import com.me.pokedex.data.database.AppDatabase
import com.me.pokedex.data.database.dao.PokemonDao
import com.me.pokedex.data.database.mapper.MapperPokemon.toPokemon
import com.me.pokedex.data.database.model.PokemonDbModel
import com.me.pokedex.presentation.domain.Pokemon
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class PokemonLocalRepositoryImpl : PokemonLocalRepository {

    private val mPokemons: MutableList<Pokemon> = mutableListOf()
    override val pokemons: MutableList<Pokemon>
        get() = mPokemons

    private val pokemonDao: PokemonDao =
        AppDatabase.getInstance(MainActivity.context).pokemonDao()

    override fun getAllPokemon(): Flow<List<Pokemon>> =
        pokemonDao.getAllPokemon()
            .map {
                Log.d(TAG, "getAllPokemon -> loading from Pokemons from database" )
                val list : MutableList<Pokemon> = mutableListOf()
                it.forEach { item ->
                    var pokemon = item.toPokemon()
                    list.add(pokemon)
                }
                return@map list
            }


    override fun getPokemonById(pokemonId: String) {
//        coroutineScope.launch {
//            val flowOfPokemon = pokemonDao?.getPokemonById(pokemonId)
//            flowOfPokemon?.collect { value ->
//                mPokemons.add(MapperPokemon.fromPokemonDbModel(value))
//            }
//        }
    }

    override suspend fun savedToPokemon(pokemon: PokemonDbModel, onDone: onDismissType) {
        pokemonDao.insert(pokemon)
    }

    private suspend fun savePokemon(pokemon: PokemonDbModel) {
        pokemonDao.insert(pokemon)
    }

    companion object {
        private const val TAG = "munky.PokemonLocalRepositoryImpl"
    }
}