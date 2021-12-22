package io.github.dox4.candybox.ui.nav

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import io.github.dox4.candybox.ui.screen.AddBookOrWorld
import io.github.dox4.candybox.ui.screen.HomeTabs

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun CandyNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeTabScreen.route) {
        composable(route = Screen.HomeTabScreen.route) {
            HomeTabs(navController = navController)
        }
        composable(route = Screen.HomeTabScreen.route + "{tab}") {
            val tab = it.arguments?.getString("tab") ?: "0"
            HomeTabs(navController = navController, tab = tab.toInt())
        }
        composable(
            route = Screen.AddBookOrWorld.route + "{index}"
        ) {
            val index = it.arguments?.getString("index") ?: throw NoIndexException
            AddBookOrWorld(navController = navController, index = index.toInt())
        }
    }
}


object NoIndexException : RuntimeException("Route to AddBookOrWorld need an index argument.")