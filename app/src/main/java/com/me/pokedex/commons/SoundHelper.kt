package com.me.pokedex.commons

import android.media.MediaPlayer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class SoundHelper {
    var currentSound: MutableState<Sound> = mutableStateOf(Sound.Intro)

    sealed class Sound (val track: Track) {
        object Snd01 : Sound(Track.snd01)
        object Snd02 : Sound(Track.snd02)
        object Snd03 : Sound(Track.snd03)
        object Snd04 : Sound(Track.snd04)
        object Snd05 : Sound(Track.snd05)
        object Snd06 : Sound(Track.snd06)
        object Intro : Sound(Track.INTRO)
    }
    private var mediaPlayer: MediaPlayer? = null
    private enum class Track {
        snd01, snd02, snd03, snd04, snd05, snd06, INTRO,
    }
    private val soundTrack = mutableListOf<String>(
        "snd01.mp3",  "snd02.mp3",  "snd03.mp3",  "snd04.mp3",  "snd05.mp3",  "snd06.mp3",  "snd_intro.mp3",
    )
    fun playerTo(selection: Sound) {
        currentSound.value = selection
    }
}
