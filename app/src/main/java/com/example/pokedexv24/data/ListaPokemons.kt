package com.example.pokedexv24.data

data class ListaPokemons(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)