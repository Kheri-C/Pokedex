package com.example.pokedexv24.data

data class ListaPokemons(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)