package com.example.pokedexv24.pantallas

//import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.pokedexv24.R
import com.example.pokedexv24.data.Result
import com.example.pokedexv24.mvvm.PokemonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun PokemonsPantalla(navController: NavController){
    val pokemonViewModel: PokemonViewModel = PokemonViewModel()
    Scaffold (
        topBar = {
            TopAppBar() {
                Icon(imageVector= Icons.Default.ArrowBack, contentDescription = "Back",
                    modifier=Modifier.clickable{
                        navController.popBackStack()
                    }
                )
                Spacer(modifier = Modifier.width (10.dp))
                Text(text = "Pokedex List")
            }
        }
    ){
        Column() {
            var state by remember { mutableStateOf("")}
            var pkmnsBuscados by remember{ mutableStateOf(pokemonViewModel.listaPokemons)}
            TextField(
                value = state,
                onValueChange = { txt ->
                    state = txt
                    pkmnsBuscados = pokemonViewModel.listaPokemons.filter { pkmn ->
                        pkmn.name.contains(txt,true)
                    }
                    Log.i("pkmns",pkmnsBuscados.toString())
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {Text("Pokemon")}
            )
            if(state == ""){
                Pokemones(pokemons = pokemonViewModel.listaPokemons)
            }else{
                Pokemones(pokemons = pkmnsBuscados)
            }

            //Pokemones(pokemons = pokemonViewModel.listaPokemons)
        }
        pokemonViewModel.getPokemons()
    }
}

@Composable
fun Pokemon (result: Result) {
    val nmb: String = result.url.substring(34).dropLast(1)
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
                    Text(text = result.name)
                    if(expanded.value){
                        //Log.i("nmb",nmb)
                        AsyncImage(
                            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$nmb.png",
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
            }
            OutlinedButton(onClick = { expanded.value=!expanded.value }){
                Text(if (expanded.value) "hide" else "catch")
            }
        }
    }
}

@Composable
private fun Pokemones (pokemons: List<Result>) {
    val scrollState = rememberScrollState()
    LazyColumn{
        itemsIndexed(items = pokemons) {index, item ->
            Pokemon(result = item)
        }
    }
}