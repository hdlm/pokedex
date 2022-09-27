package com.me.pokedex.presentation.domain

data class Pokemon (
    val id: String? = null,
    val url: String? = null ,
    val nombre: String? = null,
    val imgUrl: List<String>? = listOf(),
    val tipo: List<String>? = listOf(),
    val evolucion: String? = null, // species
    val ataques: List<String>? = listOf(),
    val habilidades: List<String>? = listOf(),
    val lugares: List<String>? = listOf(), // not implemented
)