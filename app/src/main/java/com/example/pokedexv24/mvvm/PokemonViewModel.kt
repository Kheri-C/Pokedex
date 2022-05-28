package com.example.pokedexv24.mvvm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexv24.data.Result
import com.example.pokedexv24.service.PokeMonApi
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    var listaPokemons: List<Result> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf( "")
    fun getPokemons() {
        viewModelScope.launch {
            val apiService = PokeMonApi.getInstance()
            try {
                val pokemons = apiService.getPokemonList()
                listaPokemons = pokemons.results
                Log.i( "Pokedex", listaPokemons.toString())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}