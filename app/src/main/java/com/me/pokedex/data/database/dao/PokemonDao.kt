package com.me.pokedex.data.database.dao

import androidx.room.*
import com.me.pokedex.data.database.model.PokemonDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<PokemonDbModel>>

    @Query("SELECT * FROM pokemon WHERE uid = :id ")
    fun getPokemonById(id: String) : Flow<PokemonDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonDbModel)

    fun getPokemonByIdDistinctUntilChanged(id: String) : Flow<PokemonDbModel> =
        getPokemonById(id).distinctUntilChanged()

    fun getAllPokemonDistinctUntilChanged() : Flow<List<PokemonDbModel>> =
        getAllPokemon().distinctUntilChanged()
}