package com.piscokz.Pengolah_rumus_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.piscokz.Pengolah_rumus_compose.Home.Home
import com.piscokz.Pengolah_rumus_compose.Programs.Km
//import com.piscokz.Pengolah_rumus_compose.Programs.ukuranPanjang
import com.piscokz.Pengolah_rumus_compose.Programs.RumusKelilingPersegiPanjang.Kpp

@Composable
fun Navigations() {
    val navController : NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }
        composable(route = Screen.Kpp.route) {
            Kpp(navController = navController)
        }
        composable(route = Screen.Lpp.route) {
            Lpp(navController = navController)
        }
        composable(route = Screen.Km.route) {
            Km(navController = navController)
        }

    }
}