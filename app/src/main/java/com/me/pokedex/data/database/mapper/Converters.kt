package com.me.pokedex.data.database.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class Converters : KoinComponent {
    private val gson by inject<Gson>()

    @TypeConverter
    fun toJson(list : List<String>) : String {
        return gson.toJson(list) ?: "[]"
    }

    @TypeConverter
    fun fromJson(json: String ) : List<String>? {
        val typeToken = object: TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson<List<String>>(json, typeToken) ?: emptyList()
    }
}