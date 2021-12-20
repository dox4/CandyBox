package io.github.dox4.candybox.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.dox4.candybox.domain.BookOrWorld
import io.github.dox4.candybox.ui.vm.BookOrWorldListViewModel

@Composable
fun BookTab(vm: BookOrWorldListViewModel = hiltViewModel()) {
//    val books = remember {
//        mutableStateListOf<BookOrWorld>()
//    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(vm.state.value) { _, book ->
            CardItem(item = book)
        }
    }
}

@Composable
fun WorldTab() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
    }
}

@Composable
fun OtherTab() {
}


@Composable
fun CardItem(item: BookOrWorld) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 5.dp)
    ) {
        Text(text = item.name)
    }
}