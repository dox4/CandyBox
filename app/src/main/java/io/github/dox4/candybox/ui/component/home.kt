package io.github.dox4.candybox.ui.component

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.dox4.candybox.data.vm.BookOrWorldListViewModel
import io.github.dox4.candybox.domain.BookOrWorld
import io.github.dox4.candybox.domain.BookOrWorldType

@Composable
fun BookTab(vm: BookOrWorldListViewModel = hiltViewModel()) {

    val bookOrWorldStatus by vm.bookOrWorlds.observeAsState(emptyList())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        vm.findBookOrWorld(BookOrWorldType.BOOK)
        Log.d(javaClass.canonicalName, "data read over.")
        bookOrWorldStatus?.let {
            Log.d(javaClass.canonicalName, "step in data?.let")
            itemsIndexed(it) { idx, book ->
                Log.d(javaClass.canonicalName, "got book: $idx -> ${book.name}")
                CardItem(item = book)
            }
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