package com.me.pokedex.networking.model

import com.google.gson.annotations.SerializedName

data class PokemonDto (
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonResultDto>,
)

data class PokemonDetailsDto (
    val abilities: List<PokemonAbilityModel>,
    val base_experience: Int,
    val forms: List<PokemonResultDto>,
    val game_indices: List<Object>,
    val height: Int,
    val held_items: List<Object>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<PokemonMoveDto>,
    val name: String,
    val order: Int,
    val past_types: List<Object>,
    val species: PokemonResultDto,
    val sprites: PokemonSpriteDto,
    val stats: List<Object>,
    val types: List<PokemonTypeDto>,
    val weight: Int,
)

data class PokemonSpecieDto (
    val base_happiness: Int,
    val capture_rate: Int,
    val color: PokemonResultDto,
    val egg_groups: List<PokemonResultDto>,
    val evolution_chain: String,
    val envolves_from_species: String?,
    val flavor_text_entries: PokemonFlavorDto,
)



data class PokemonResultDto (
    val name: String,
    val url: String,
)
data class PokemonAbilityModel (
    val ability: PokemonResultDto,
    val is_hidden: Boolean,
    val slot: Int,
)
data class PokemonSpriteDto (
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?,
    val other: Object,
    val versions: Object,
)
data class PokemonTypeDto (
    val slot: Int,
    val type: PokemonResultDto,
)
data class PokemonMoveDto (
    val move: PokemonResultDto,
    val version_group_details: List<Object>
)
data class PokemonFlavorDto (
    val flavor_text: String,
    val language: PokemonResultDto,
    val version: PokemonResultDto
)
data class PokemonEncounterDto (
    val location_area : PokemonResultDto,
    val version_details : List<Object>
)
