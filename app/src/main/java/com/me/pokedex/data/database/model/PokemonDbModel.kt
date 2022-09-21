package com.me.pokedex.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonDbModel (
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "tipo") val tipo: String?,
    @ColumnInfo(name = "evolucion") val evolucion: String?,
    @ColumnInfo(name = "ataques") val ataques: String?,
    @ColumnInfo(name = "habilidades") val habilidades: String?,
    @ColumnInfo(name = "lugares")  val lugares: String?
)
