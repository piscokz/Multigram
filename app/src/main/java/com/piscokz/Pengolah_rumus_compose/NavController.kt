package com.piscokz.Pengolah_rumus_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.piscokz.Pengolah_rumus_compose.ui.Home.Home
import com.piscokz.Pengolah_rumus_compose.Programs.Km
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterByte.Kb
//import com.piscokz.Pengolah_rumus_compose.Programs.ukuranPanjang
import com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusKelilingPersegiPanjang.Kpp
import kotlinx.serialization.Serializable

@Composable
fun NavController() {
    val navController : NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {

        composable<Home> {
            Home(navController = navController)
        }
        composable<Kpp> {
            val data = it.toRoute<Kpp>()
            Kpp(data, navController = navController)
        }
        composable<Lpp> {
            val data = it.toRoute<Lpp>()
            Lpp(data, navController = navController)
        }
        composable<Km> {
            val data = it.toRoute<Km>()
            Km(data, navController = navController)
        }
        composable<Kb> {
            val data = it.toRoute<Kb>()
            Kb(data, navController = navController)
        }
    }
}

@Serializable
object Home

@Serializable
data class Kpp(
    val judul : String,
)

@Serializable
data class Lpp(
    val judul : String,
)

@Serializable
data class Km(
    val judul : String,
)

@Serializable
data class Kb(
    val judul : String,
)