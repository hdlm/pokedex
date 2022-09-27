package com.me.pokedex.data.database.mapper

import com.me.pokedex.data.database.model.PokemonDbModel
import com.me.pokedex.networking.model.*
import com.me.pokedex.presentation.domain.Pokemon
import java.util.*

object MapperPokemon {

    // extensions
    fun PokemonDetailsDto.toPokemon() : Pokemon {

        val fnToListOfUrlImages : (PokemonSpriteDto) -> List<String>? = { sprite ->
            var imgUrls : MutableList<String>? = mutableListOf()
            imgUrls?.let {
                imgUrls.add(sprite.front_default!!)
                imgUrls.add(sprite.back_default!!)
                imgUrls
            } ?: run { null }
        }
        val fnToListTipo : (List<PokemonTypeDto>) -> List<String> = { pokemonTypes ->
            var tipos : MutableList<String> = mutableListOf()
            pokemonTypes.forEachIndexed { index, tipeModel ->
                tipos.add(tipeModel.type.name)
            }
            tipos
        }
        val fnToListAtaques : (List<PokemonMoveDto>) -> List<String> = { moveModels ->
            var ataques : MutableList<String> = mutableListOf()
            moveModels.forEach { item ->
                ataques.add(item.move.name)
            }
            ataques
        }
        val fnToListHabilidades : (List<PokemonAbilityModel>) -> List<String> = { abilitiesModel ->
            var habilidades : MutableList<String> = mutableListOf()
            abilitiesModel.forEach { item ->
                habilidades.add(item.ability.name)
            }
            habilidades
        }

        var pokemon : Pokemon? = null
        this.apply {
            pokemon = Pokemon(
                id = UUID.randomUUID().toString(),
                nombre = name,
                imgUrl = fnToListOfUrlImages(sprites),
                tipo = fnToListTipo(types),
                evolucion = species.name,
                ataques = fnToListAtaques(moves),
                habilidades = fnToListHabilidades(abilities),
                lugares = null,
            )
        }
        return pokemon!!
    }

    fun Pokemon.toPokemonDb() : PokemonDbModel {
        this.apply {
            val pokemonModel = PokemonDbModel(
                uid = UUID.randomUUID().toString(),
                fkId = url!!,
                nombre = nombre!!,
                imgUrl = imgUrl,
                tipo = tipo?.let {
                    if (tipo.isEmpty()) null
                    else it
                },
                evolucion = evolucion!!,
                ataques = ataques?.let {
                    if (ataques.isEmpty()) null
                    else it
                },
                habilidades = habilidades?: run { emptyList() },
                lugares = null,
            )
            return pokemonModel
        }
    }

    fun PokemonDbModel.toPokemon() : Pokemon {
        this.apply {
            val pokemon = Pokemon(
                id = uid,
                url = fkId,
                nombre = nombre,
                imgUrl = imgUrl,
                tipo = tipo,
                evolucion = evolucion!!,
                ataques = ataques,
                habilidades = habilidades,
                lugares = null
            )
            return pokemon
        }
    }

}