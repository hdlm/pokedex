package com.me.pokedex.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonDbModel (
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "fk_id") val fkId: String?,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "url_image") val imgUrl: List<String>?,
    @ColumnInfo(name = "tipo") val tipo: List<String>?,
    @ColumnInfo(name = "evolucion") val evolucion: String,
    @ColumnInfo(name = "ataques") val ataques: List<String>?,
    @ColumnInfo(name = "habilidades") val habilidades: List<String>?,
    @ColumnInfo(name = "lugares")  val lugares: String?,
)
