package com.me.pokedex.di

import com.me.pokedex.data.database.repository.PokemonRepository
import com.me.pokedex.data.database.repository.PokemonRepositoryImpl
import com.me.pokedex.presentation.MainViewModel
import org.koin.dsl.module

object Modules {
    val appModule = module {
        single { PokemonRepositoryImpl() }
        single { MainViewModel(get()) }
    }

    val unitTestModule = module {
        factory<PokemonRepository> { PokemonRepositoryImpl() }
    }
}