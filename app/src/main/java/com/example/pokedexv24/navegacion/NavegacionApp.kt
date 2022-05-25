package com.example.pokedexv24.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexv24.pantallas.PokemonsPantalla
import com.example.pokedexv24.pantallas.PrimeraPantalla

@Composable
fun NavegacionApp(){
    val navController = rememberNavController()
    NavHost(navController=navController,startDestination=
            NavegacionPantallas.PrimeraPantalla.pantalla){
        composable(route = NavegacionPantallas.PrimeraPantalla.pantalla){
            PrimeraPantalla(navController)
    }
    composable(route= NavegacionPantallas.PokemonsPantalla.pantalla) {
        PokemonsPantalla(navController)
     }
    }
}

/*
rememberNavController returns a NavHostController which is a subclass of
NavController that offers some additional APIs that a NavHost can use.

Each NavController must be associated with a single NavHost composable.
The NavHost links the NavController with a navigation graph that specifies
the composable destinations that you should be able to navigate between.
As you navigate between composables, the content of the NavHost is automatically
recomposed. Each composable destination in your navigation graph is
associated with a route.
Creating the NavHost requires the NavController previously created
via rememberNavController() and the route of the starting destination of your graph.
 */
