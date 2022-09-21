package com.me.pokedex.data.database.mapper

import com.me.pokedex.data.database.model.PokemonDbModel
import com.me.pokedex.networking.model.PokemonModel
import java.util.*

object MapperPokemon {
    // extensions
    fun PokemonModel.toPokemonDb() : PokemonDbModel = with(this) {
        PokemonDbModel(
            uid = run {
                id?.let { it } ?: run { UUID.randomUUID().toString()}
            },
            tipo = tipo,
            evolucion = evolucion,
            ataques = ataques,
            habilidades = habilidades,
            lugares = lugares
        )
    }

    fun fromPokemonDbModel (pokemonDbModel: PokemonDbModel) : PokemonModel = with (pokemonDbModel) {
        PokemonModel(
            id = uid,
            tipo = tipo!!,
            evolucion = evolucion!!,
            ataques = ataques!!,
            habilidades = habilidades!!,
            lugares = lugares!!
        )
    }
}