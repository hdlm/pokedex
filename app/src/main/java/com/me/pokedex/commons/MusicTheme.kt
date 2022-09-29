package com.me.pokedex.commons

import android.media.MediaPlayer
import com.me.pokedex.MainActivity
import com.me.pokedex.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object MusicTheme {

    private var isMusicOn = false
    private lateinit var mediaPlayer : MediaPlayer

    fun playIntro() {

        GlobalScope.launch(Dispatchers.Main) {
            if (!isMusicOn) {
                isMusicOn = true
                mediaPlayer = MediaPlayer.create(MainActivity.context, R.raw.snd_intro)
                mediaPlayer.start()
                delay(11200)
                mediaPlayer.stop()
            }
        }

    }
}