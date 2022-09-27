package com.me.pokedex.commons

import android.annotation.SuppressLint
import contextProvider.CoroutineContextProvider
import contextProvider.CoroutineContextProviderImpl
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

/**
 * La clase permite ejecutar las pruebas dentro de un mismo entorno para soportar el debugging de las coroutines
 */
class CoroutineHelper {

    var coroutineScope: CoroutineScope? = null
        private set
    var coroutineContextProvider: CoroutineContextProvider? = null
        private set


    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        private var instance: CoroutineHelper? = null
        private val coroutineJob = Job()

        fun make(dispatcher: CoroutineDispatcher): CoroutineHelper {
            val instance = CoroutineHelper()
            instance.coroutineScope = CoroutineScope(dispatcher)
            instance.coroutineContextProvider = CoroutineContextProviderImpl(dispatcher)
            return instance
        }

        fun make(): CoroutineHelper {
            val instance = CoroutineHelper()
            instance.coroutineScope = CoroutineScope(( Dispatchers.IO + coroutineJob) )
            instance.coroutineContextProvider = CoroutineContextProviderImpl(Dispatchers.IO)
            return instance
        }

    }
}