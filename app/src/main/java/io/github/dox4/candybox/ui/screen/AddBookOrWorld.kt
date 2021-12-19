package io.github.dox4.candybox.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.dox4.candybox.ui.component.TopAppBarText
import io.github.dox4.candybox.ui.nav.Screen
import io.github.dox4.candybox.ui.vm.BookOrWorldViewModel
import io.github.dox4.candybox.util.HOME_TAB_TITLES

@Composable
fun AddBookOrWorld(
    navController: NavController,
    index: Int,
    vm: BookOrWorldViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopAppBarText(
                        text = "新增 " + HOME_TAB_TITLES[index]
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.HomeTabScreen.route)
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "后退")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
            }) {
                Icon(imageVector = Icons.Filled.Save, contentDescription = "保存")
            }
        }
    ) {


    }
}