package com.me.pokedex.di

import com.google.gson.Gson
import com.me.pokedex.data.database.dao.PokemonDao
import com.me.pokedex.data.database.dao.PokemonDao_Impl
import com.me.pokedex.data.repository.PokemonLocalRepository
import com.me.pokedex.data.repository.PokemonLocalRepositoryImpl
import com.me.pokedex.data.repository.PokemonRepository
import com.me.pokedex.data.repository.PokemonRepositoryImpl
import com.me.pokedex.networking.ApiAdapter
import com.me.pokedex.networking.PokedexApiAdapter
import com.me.pokedex.presentation.MainViewModel
import com.me.pokedex.presentation.usecase.FetchPokemonsDataUseCase
import com.me.pokedex.presentation.usecase.FetchPokemonsDataUseCaseImpl
import org.koin.dsl.module

object Modules {
    val appModule = module {
        factory { Gson() }
        single<ApiAdapter> { PokedexApiAdapter() }
        single<PokemonLocalRepository> { PokemonLocalRepositoryImpl() }
        single<PokemonRepository> { PokemonRepositoryImpl() }
        single<FetchPokemonsDataUseCase> { FetchPokemonsDataUseCaseImpl() }
        single { MainViewModel(get()) }
    }

    val unitTestModule = module {
        factory { Gson() }
        factory<ApiAdapter> { PokedexApiAdapter() }
        factory<PokemonLocalRepository> { PokemonLocalRepositoryImpl() }
        factory<PokemonRepository> { PokemonRepositoryImpl() }
        factory<FetchPokemonsDataUseCase> { FetchPokemonsDataUseCaseImpl() }
        single { MainViewModel(get()) }
    }
}