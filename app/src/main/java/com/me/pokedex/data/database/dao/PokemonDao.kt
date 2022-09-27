package com.me.pokedex.data.database.dao

import androidx.room.*
import com.me.pokedex.data.database.model.PokemonDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemons: List<PokemonDbModel>)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<PokemonDbModel>>

    @Query("SELECT * FROM pokemon WHERE uid = :id ")
    fun getPokemonById(id: String) : Flow<PokemonDbModel>

    @Query("DELETE FROM pokemon")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAllAndUpdate(pokemons: List<PokemonDbModel>) {
        deleteAll()
        insert(pokemons)
    }

}