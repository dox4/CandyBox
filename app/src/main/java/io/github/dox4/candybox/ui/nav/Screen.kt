package io.github.dox4.candybox.ui.nav

sealed class Screen(val route: String) {
    object HomeTabScreen : Screen("home_tab")
    object AddBookOrWorld : Screen("add_book_or_world/")
}