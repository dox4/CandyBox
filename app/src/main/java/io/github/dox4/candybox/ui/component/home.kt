package io.github.dox4.candybox.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navArgument
import io.github.dox4.candybox.data.vm.BookListViewModel
import io.github.dox4.candybox.data.vm.WorldListViewModel
import io.github.dox4.candybox.domain.BookOrWorld
import io.github.dox4.candybox.domain.BookOrWorldType
import io.github.dox4.candybox.ui.nav.Screen
import io.github.dox4.candybox.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun BookTab(
    navController: NavController,
    vm: BookListViewModel = hiltViewModel()
) {
    val bookOrWorldStatus by vm.books.observeAsState(emptyList())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        vm.findBooks()
        bookOrWorldStatus?.let {
            itemsIndexed(it) { _, book ->
                CardItem(navController = navController, item = book)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun WorldTab(navController: NavController, vm: WorldListViewModel = hiltViewModel()) {
    val bookOrWorldStatus by vm.worlds.observeAsState(emptyList())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        vm.findWorlds()
        bookOrWorldStatus?.let {
            itemsIndexed(it) { _, world ->
                CardItem(navController = navController, item = world)
            }
        }
    }
}

@Composable
fun OtherTab() {
}


@ExperimentalMaterialApi
@Composable
fun CardItem(item: BookOrWorld, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(CardPadding),
        elevation = CardElevation,
        shape = CardShape,
        onClick = {
            navController.navigate(Screen.TemplateTypeTab.route + item.id) {
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (item.type == BookOrWorldType.BOOK) BookCardColor else WorldCardColor)
                .padding(16.dp),
            content = {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.desc,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
//                            onDeleteClick()
                        }
                )
            }
        )
    }
}