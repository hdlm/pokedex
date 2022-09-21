package com.me.pokedex.data.database.repository

import com.me.pokedex.MainActivity
import com.me.pokedex.commons.onDismissType
import com.me.pokedex.data.database.AppDatabase
import com.me.pokedex.data.database.dao.PokemonDao
import com.me.pokedex.data.database.mapper.MapperPokemon
import com.me.pokedex.data.database.model.PokemonDbModel
import com.me.pokedex.networking.model.PokemonModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

open class PokemonRepositoryImpl : PokemonRepository {
    protected val mPokemons: MutableList<PokemonModel> = mutableListOf()

    private val coroutineJob = Job()
    private val coroutineScope = CoroutineScope( (Dispatchers.IO + coroutineJob))

    private val pokemonDao: PokemonDao =
        AppDatabase.getInstance(MainActivity.context).pokemonDao()

    override val pokemons : MutableList<PokemonModel>
        get() = mPokemons

    override fun getAllPokemon() {
        coroutineScope.launch {
            val flowOfPokemons = pokemonDao?.getAllPokemon()
            flowOfPokemons?.collect { value ->
                value.forEach {
                    var pokemon = MapperPokemon.fromPokemonDbModel(it)
                    if (mPokemons.indexOf(pokemon) == -1) { // el registro no existe en la lista, agregarlo
                        mPokemons.add(pokemon)
                    }
                }
            }
        }
    }


    override fun getPokemonById(pokemonId: String) {
        coroutineScope.launch {
            val flowOfPokemon = pokemonDao?.getPokemonById(pokemonId)
            flowOfPokemon?.collect { value ->
                mPokemons.add(MapperPokemon.fromPokemonDbModel(value))
            }
        }
    }

    private suspend fun insert(pokemon: PokemonDbModel) {
        pokemonDao.insert(pokemon)
    }

    override fun savedToPokemon(pokemon: PokemonDbModel, onDone: onDismissType) {
        coroutineScope.launch {
            insert(pokemon)
            delay(200)
            /* onDone.invoke() */
        }
    }




}