package com.piscokz.Pengolah_rumus_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.piscokz.Pengolah_rumus_compose.ui.Home.Home
import com.piscokz.Pengolah_rumus_compose.Programs.Km
import com.piscokz.Pengolah_rumus_compose.ui.Programs.BMI.BmiScreen
import com.piscokz.Pengolah_rumus_compose.ui.Programs.Calculator.CalculatorScreen
import com.piscokz.Pengolah_rumus_compose.ui.Programs.HitungDiskon.HdScreen
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterByte.Kb
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterGram.KonverterGramScreen
import com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusKelilingPersegiPanjang.Kpp
import com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusPersegi.PersegiScreen
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
        composable<Hd> {
            val data = it.toRoute<Hd>()
            HdScreen(data = data, navController = navController)
        }
        composable<Calculator> {
            val data = it.toRoute<Calculator>()
            CalculatorScreen(data = data, navController = navController)
        }
        composable<Gram> {
            val data = it.toRoute<Gram>()
            KonverterGramScreen(data = data, navController = navController)
        }

        composable<BMI> {
            val data = it.toRoute<BMI>()
            BmiScreen(data = data, navController)
        }
        composable<Circle> {
            val data = it.toRoute<Circle>()
        }
        composable<Square> {
            val data = it.toRoute<Square>()
            PersegiScreen(data = data, navController)
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

@Serializable
data class Hd (
    val judul: String
)

@Serializable
data class Calculator (
    val judul: String
)

@Serializable
data class Gram (
    val judul: String
)

@Serializable
data class BMI (
    val judul: String
)

@Serializable
data class Circle (
    val judul: String
)

@Serializable
data class Square (
    val judul: String
)
