package com.me.pokedex.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.me.pokedex.data.database.dao.PokemonDao
import com.me.pokedex.data.database.mapper.Converters
import com.me.pokedex.data.database.model.PokemonDbModel

@Database(entities = [PokemonDbModel::class], version =  1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao

    companion object {
        const val DATABASE_NAME = "pokedexdb"

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
        }
    }
}