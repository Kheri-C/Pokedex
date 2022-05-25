package com.example.pokedexv24.navegacion

sealed class NavegacionPantallas (val pantalla : String) {
    object PrimeraPantalla : NavegacionPantallas(  "primera_pantalla")
    object PokemonsPantalla : NavegacionPantallas (  "pokemons_pantalla")
}
/*
Sealed classes and interfaces represent restricted class hierarchies
 that provide more control over inheritance.

Sealed class is a class which restricts the class hierarchy. A class c
an be declared as sealed class using "sealed" keyword before the class name.
It is used to represent restricted class hierarchy.

Sealed class is used when the object have one of the types from limited set, but cannot have any other type.


 */