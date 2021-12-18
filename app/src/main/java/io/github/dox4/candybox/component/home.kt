package io.github.dox4.candybox.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import io.github.dox4.candybox.MainActivity.Companion.db
import io.github.dox4.candybox.entity.BookOrWorld
import io.github.dox4.candybox.util.TAB_FONT_SIZE
import io.github.dox4.candybox.util.TOP_BAR_FONT_SIZE
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home() {
    val tabTitle = arrayOf("书/小说", "世界", "其他")
    val pagerState = rememberPagerState(initialPage = 0)
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            tabTitle.forEachIndexed { index, tabName ->
                Tab(selected = tabIndex == index, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = {
                    Text(
                        text = tabName,
                        fontSize = TAB_FONT_SIZE
                    )
                })
            }
        }
        HorizontalPager(
            count = tabTitle.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Log.d(javaClass.canonicalName, "to index: $index")
                when (index) {
                    0 -> BookTab()
                    1 -> WorldTab()
                    2 -> OtherTab()
                    else -> throw IndexOutOfBoundsException()
                }
//                tabMap[tabTitle[index]]?.let {
//                    CustomView(
//                        viewModel = SavedViewModel(),
//                        properties = it
//                    )
//                }
            }
        }
    }
}


@Composable
fun BookTab() {
    val books = db.bwDao().findBooks()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(books) { _, book ->
            CardItem(item = book)
        }
    }
}

@Composable
fun WorldTab() {
    val worlds = db.bwDao().findWorlds()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(worlds) { _, world ->
            CardItem(item = world)
        }
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

@Composable
fun AddItemDialog(label: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    var text by remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 2.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "添加项目：$label",
                    fontSize = TAB_FONT_SIZE,
                    modifier = Modifier.padding(0.dp, 2.dp)
                )
            }
        },
        text = {
            Row(modifier = Modifier.padding(0.dp, 2.dp)) {
                OutlinedTextField(
                    value = text,
                    label = { Text(text = "名称") },
                    onValueChange = { text = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 2.dp)
                )
            }
        },
        buttons = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(onClick = onDismiss) {
                    Text(text = "取消", fontSize = TAB_FONT_SIZE)
                }
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = onConfirm) {
                    Text(text = "确定", fontSize = TAB_FONT_SIZE)
                }
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    )
}


@ExperimentalComposeUiApi
@Composable
fun HomeScaffold() {
    var openDialog by remember {
        mutableStateOf(false)
    }
    if (openDialog) {
        AddItemDialog(label = "书", onDismiss = { openDialog = false }) {
            Log.d("OhMyAlert", "opened")
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "CandyBox", fontSize = TOP_BAR_FONT_SIZE) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                openDialog = true
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }) {
        Home()
    }
}