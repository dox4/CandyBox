package io.github.dox4.candybox.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import io.github.dox4.candybox.data.vm.BookOrWorldViewModel
import io.github.dox4.candybox.domain.GroupedTypes
import io.github.dox4.candybox.domain.TemplateType
import io.github.dox4.candybox.ui.component.TopAppBarText
import io.github.dox4.candybox.ui.theme.*
import io.github.dox4.candybox.util.TAB_FONT_SIZE
import io.github.dox4.candybox.util.TEMPLATE_TYPE_TITLES
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TemplateTypeTab(
    navController: NavController,
    bowId: String,
    viewModel: BookOrWorldViewModel = hiltViewModel()
) {
    viewModel.findBookOrWorld(bowId)
    val tabTitle = TEMPLATE_TYPE_TITLES
    val pagerState = rememberPagerState(initialPage = 0)
    val tabIndex = pagerState.currentPage
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopAppBarText(
                        text = ""
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "后退")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Log.d(
                    "fabClicked",
                    "current tab is ${pagerState.currentPage}, tabIndex = $tabIndex"
                )
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
        ) {

            val coroutineScope = rememberCoroutineScope()
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = viewModel.state.value.name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(horizontal = 16.dp),
                    text = viewModel.state.value.desc,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TabRow(
                    selectedTabIndex = tabIndex,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                        )
                    }
                ) {
                    tabTitle.forEachIndexed { index, tabName ->
                        Log.d(javaClass.canonicalName, "$index: $tabName")
                        Tab(
                            selected = tabIndex == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                Text(
                                    text = tabName,
                                    fontSize = TAB_FONT_SIZE
                                )
                            },
                            modifier = Modifier.background(color = SimpleTabBackground)
                        )
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
                        TemplateTypeCards(templates = GroupedTypes[index])
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TemplateTypeCards(templates: Array<TemplateType>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(templates) { _, t ->
            SimpleCard(type = t)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SimpleCard(type: TemplateType) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(CardPadding),
        elevation = CardElevation,
        shape = CardShape,
        onClick = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = SimpleCardColor)
                .padding(16.dp),
            content = {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = type.hans,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        )
    }
}