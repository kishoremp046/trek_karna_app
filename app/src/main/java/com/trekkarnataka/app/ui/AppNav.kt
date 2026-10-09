package com.trekkarnataka.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.trekkarnataka.app.ui.screens.*

@Composable
fun AppNav(vm: TrekViewModel) {
    val nav = rememberNavController()
    NavHost(nav, startDestination = "landing") {
        composable("landing") { LandingScreen { nav.navigate("districts") } }
        composable("districts") {
            DistrictScreen(vm, onBack = { nav.popBackStack() }) { nav.navigate("treks/${android.net.Uri.encode(it)}") }
        }
        composable("treks/{district}") { e ->
            val d = e.arguments?.getString("district") ?: ""
            TrekListScreen(d, vm.treks(d), onBack = { nav.popBackStack() }) { nav.navigate("detail/$it") }
        }
        composable("detail/{id}") { e ->
            DetailScreen(vm.trek(e.arguments?.getString("id") ?: ""), onBack = { nav.popBackStack() })
        }
    }
}
