package com.example.pokedexv24.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pokedexv24.navegacion.NavegacionPantallas

@Composable
fun PrimeraPantalla(navController: NavController){
    Scaffold(topBar = {
        //scaffold es un layout que permite implementar distintos componentes
        //gráficos previamente definidos acomodándolos en la pantalla de acuerdo a los lineamientos de material
        //design
        TopAppBar(){
            Text(text = "Pokedex")
        }
    }) {
        Detalle(navController)
    }
}

@Composable
fun Detalle(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Primera Pantalla")
        Button (onClick = { navController.navigate (route= NavegacionPantallas.PokemonsPantalla.pantalla) }) {
            Text(text = "Click para ver pokemons")
        }
    }
}

/*
Surface: Es algo equivalente al Box, pero que implementa
las ideas detrás de las superficies de Material.
Gracias a usar Surface, se pueden aplicar mejor la elevación de la
superficie, los bordes, es más inteligente en la elección de los colores para el tema…

Row y Column: sustituyen al LinearLayout. Organizan elementos de forma
consecutiva, o unos al lado de otros o apilados. También se pueden
aplicar pesos para definir cuánto ocupará cada elemento


The outline button: style can be used to show a button
similar to that of the Contained button, with the difference of
a stroke being used to outline the body of the button.

When the user presses the Back button on their device, or when you call
FragmentManager.popBackStack(), the top-most fragment transaction
is popped off of the stack. In other words, the transaction is reversed.
If there are no more fragment transactions on the stack, and if you aren't
using child fragments, the back event bubbles up to the activity.

 */