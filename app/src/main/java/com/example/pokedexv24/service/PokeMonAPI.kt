package com.example.pokedexv24.service

import com.example.pokedexv24.data.ListaPokemons
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokeMonApi {
    @GET("pokemon")
    suspend fun getPokemonList(): ListaPokemons

    companion object {
        var apiService: PokeMonApi? = null
        fun getInstance(): PokeMonApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PokeMonApi::class.java)
            }
            return apiService!!
        }
    }
}