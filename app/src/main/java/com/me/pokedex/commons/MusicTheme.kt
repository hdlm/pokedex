package com.me.pokedex.commons

import android.media.MediaPlayer
import com.me.pokedex.MainActivity
import com.me.pokedex.R

object MusicTheme {
    private var isMusicOn = false
    private lateinit var mediaPlayer : MediaPlayer

    fun playIntro() {
        if(!isMusicOn) {
            isMusicOn = true
            mediaPlayer = MediaPlayer.create(MainActivity.context, R.raw.snd_intro)
            mediaPlayer.start()
            Thread.sleep(11200)
            mediaPlayer.stop()
        }
    }
}