package com.me.pokedex.presentation

import com.me.pokedex.di.Modules
import com.me.pokedex.presentation.usecase.FetchPokemonsDataUseCase
import contextProvider.CoroutineContextProviderImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class MainViewModelTest : KoinComponent {

    private val testCoroutineDispatcher: CoroutineDispatcher = StandardTestDispatcher()
    private var testCoroutineScope = TestScope(testCoroutineDispatcher)
    private val testCoroutineContextProvider = CoroutineContextProviderImpl(testCoroutineDispatcher)
    private val fetchPokemonsDataUseCase = get<FetchPokemonsDataUseCase>()
    private val viewModel = get<MainViewModel>()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
//                androidLogger()
//                androidContext(this@StartKoinForTest)
                modules(Modules.unitTestModule)
                printLogger()
            }
        }
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getData calls repository to get data`() = testCoroutineScope.runTest {
        viewModel.getFreshData()

        yield()

        assertEquals("El valor esperado no coincide", 3, fetchPokemonsDataUseCase.repository.pokemons.size)
    }
}

