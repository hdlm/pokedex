package com.me.pokedex.presentation

import androidx.lifecycle.ViewModel
import com.me.pokedex.data.database.repository.PokemonRepositoryImpl
import com.me.pokedex.networking.model.PokemonModel

class MainViewModel (
    private val repository: PokemonRepositoryImpl,
): ViewModel() {
    val pokemons: List<PokemonModel>
        get() = repository.pokemons

    /**
     * Data-binding [MainViewModel]ViewModel
     */
    var onPokemonUpdated: ( (List<PokemonModel>) -> Unit )? = null
        set(value) {
            field = value
            onPokemonUpdated?.invoke(pokemons)
        }

    fun retrievePokemons() {
        repository.getAllPokemon()
    }

    fun savePokemon(pokemon: PokemonModel) {
        //TODO guardar el pokemon a la BD
//        repository.savedToPokemon(pokemonDb, onDismissType)
    }
}

