package io.github.dox4.candybox.ui.nav

sealed class Screen(val route: String) {
    object HomeTabScreen : Screen("home_tab")
    object AddBookOrWorld : Screen("add_book_or_world/")
    object TemplateTypeTab : Screen("template_tab/")
    object TemplateList : Screen("template_list")
    object AddTemplate : Screen("add_template/")
}