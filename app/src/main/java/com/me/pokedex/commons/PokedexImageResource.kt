package com.me.pokedex.commons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.me.pokedex.R


object PokeImageResources {

    val items = listOf(
        PokeImage(R.string.aipom, R.drawable.ic_aipom),
        PokeImage(R.string.arbok, R.drawable.ic_arbok),
        PokeImage(R.string.beedrill, R.drawable.ic_beedrill),
        PokeImage(R.string.bulbasaur, R.drawable.ic_bulbasaur),
        PokeImage(R.string.butterfree, R.drawable.ic_butterfree),
        PokeImage(R.string.caterpie, R.drawable.ic_caterpie),
        PokeImage(R.string.charizard, R.drawable.ic_charizard),
        PokeImage(R.string.cloyster, R.drawable.ic_cloyster),
        PokeImage(R.string.cyndaquil, R.drawable.ic_cyndaquil),
        PokeImage(R.string.exeggutor, R.drawable.ic_exeggutor),
        PokeImage(R.string.farfetchd, R.drawable.ic_farfetchd),
        PokeImage(R.string.gastly, R.drawable.ic_gastly),
        PokeImage(R.string.gengar, R.drawable.ic_gengar),
        PokeImage(R.string.golem, R.drawable.ic_golem),
        PokeImage(R.string.pichu, R.drawable.ic_pichu),
        PokeImage(R.string.wigglytuff, R.drawable.ic_wigglytuff),
        PokeImage(R.string.zubat, R.drawable.ic_zubat),
    )

}


data class PokeImage (
    @StringRes val pokeNameResourceId: Int,
    @DrawableRes val pokeImageResourceId: Int
)
