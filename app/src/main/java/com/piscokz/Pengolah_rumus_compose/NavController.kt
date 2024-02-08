package com.piscokz.Pengolah_rumus_compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }
        composable(route = Screen.Rumus.route) {
            RumusKelilingPersegi(navController)
        }
//        composable(
//            route = Screen.Detail.route + "/{name}",
//            arguments = listOf(
//                navArgument("name") {
//                    type = NavType.StringType
//                    defaultValue = "Master"
//                    nullable = true
//                }
//            )
//        ) { entry ->
//            DetailScreen(string = entry.arguments?.getString("name"), navController)
//        }

    }
}