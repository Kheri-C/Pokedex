package com.example.pokedexv24.pantallas

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedexv24.data.APIService
import com.example.pokedexv24.data.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable

fun PokemonsPantalla(navController: NavController){
    Scaffold (topBar = {

    TopAppBar() {

        Icon(imageVector= Icons.Default.ArrowBack, contentDescription = "Back",
        modifier=Modifier.clickable{
                navController.popBackStack()
        })

        Spacer(modifier = Modifier.width (10.dp))

        Text(text = "Pokedex List")

    }

}){
 Pokemones()
}

}


@Composable
fun Pokemon (name: String) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(color=MaterialTheme.colors.primary,
        modifier = Modifier.padding (vertical = 5.dp, horizontal = 10.dp)) {
        Row(modifier = Modifier.padding (25.dp)) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ){
                    Text(text = "Pokemon: ")
                    Text(text = name)
                }
            OutlinedButton(onClick = { expanded.value=!expanded.value }){
                Text(if (expanded.value) "hide" else "catch")


            }
        }
    }
}



@Composable
private fun Pokemones (cameos: List<String> = List( 10) { "$it" }) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .verticalScroll(scrollState)
    ) {
        for (cameo in cameos) {
            getAllData(cameo)

            Pokemon(cameo)
        }
    }
}

lateinit var results: Results

fun getAllData(pokemon: String) {


    val callToService = getRetrofit().create(APIService::class.java)

    CoroutineScope(Dispatchers.IO).launch {
        val responseFromService = callToService.getPokemons("$pokemon/")
            results = responseFromService.body() as Results

            if (responseFromService.isSuccessful) {
                Log.i("Pokemons", results.name)

            }
    }
}


private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/pokemon/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

