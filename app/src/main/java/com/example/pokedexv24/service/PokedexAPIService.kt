package com.example.pokedexv24.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPokemons(@Url url: String) : Response<Results>
}