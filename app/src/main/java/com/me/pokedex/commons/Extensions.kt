package com.me.pokedex.commons

import android.media.MediaPlayer

/**
 * Get MediaPlayer time duration in seconds
 */
val MediaPlayer.seconds: Int
    get() {
        return this.duration / 1000
    }
