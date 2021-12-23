package io.github.dox4.candybox.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import io.github.dox4.candybox.ui.component.BookTab
import io.github.dox4.candybox.ui.component.OtherTab
import io.github.dox4.candybox.ui.component.TopAppBarText
import io.github.dox4.candybox.ui.component.WorldTab
import io.github.dox4.candybox.ui.nav.Screen
import io.github.dox4.candybox.util.HOME_TAB_TITLES
import io.github.dox4.candybox.util.TAB_FONT_SIZE
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun HomeTabs(navController: NavHostController) {
    val tabTitle = HOME_TAB_TITLES
    val pagerState = rememberPagerState(initialPage = 0)
    val tabIndex = pagerState.currentPage
    Scaffold(
        topBar = {
            TopAppBar(title = {
                TopAppBarText(text = "CandyBox")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Log.d(
                    "fabClicked",
                    "current tab is ${pagerState.currentPage}, tabIndex = $tabIndex"
                )
                if (tabIndex != 2) {
                    navController.navigate(Screen.AddBookOrWorld.route + tabIndex)
                }
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        }
    ) {
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
                    Log.d(javaClass.canonicalName, "$index: $tabName")
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
                    when (index) {
                        0 -> BookTab(navController)
                        1 -> WorldTab(navController)
                        2 -> OtherTab()
                        else -> throw IndexOutOfBoundsException()
                    }
                }
            }
        }

    }
}