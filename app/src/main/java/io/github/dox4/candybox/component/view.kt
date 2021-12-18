package io.github.dox4.candybox.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import io.github.dox4.candybox.entity.Property
import io.github.dox4.candybox.ui.theme.CandyBoxTheme
import io.github.dox4.candybox.viewmodel.SavedViewModel
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalComposeUiApi
@Composable
fun CustomView(viewModel: SavedViewModel, properties: List<Property>) = CandyBoxTheme {
    val focusManager = LocalFocusManager.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ) {
        PropertyFields(
            list = properties, viewModel = viewModel
        )
    }
}


@Composable
fun TitleView(title: String) {
    Text(
        title, style = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W900,
            fontSize = 16.sp,
            color = Color.Black,
        ), modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    )
}

@ExperimentalComposeUiApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabWithPager(tabMap: Map<UUID, List<Property>>) {
    val tabTitle = tabMap.keys.sorted()
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
                    Text(text = "tab")
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
                tabMap[tabTitle[index]]?.let {
                    CustomView(
                        viewModel = SavedViewModel(),
                        properties = it
                    )
                }
            }
        }
    }
}
