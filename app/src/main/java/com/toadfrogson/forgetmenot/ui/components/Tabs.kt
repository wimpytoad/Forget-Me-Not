package com.toadfrogson.forgetmenot.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.toadfrogson.forgetmenot.R
import com.toadfrogson.forgetmenot.ui.theme.*
import kotlinx.coroutines.launch


@Composable
@ExperimentalPagerApi
fun Tabs(pagerState: PagerState) {
    val tabItems = listOf(
        stringResource(id = R.string.task_tab_generic) to Icons.Default.ShoppingCart,
        stringResource(id = R.string.task_tab_business) to Icons.Default.Phone,
        stringResource(id = R.string.task_tab_social) to Icons.Default.Person
    )

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = WhiteBackgroundColor,
        contentColor = SecondaryTextColor,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                icon = {
                    Icon(imageVector = item.second, contentDescription = null)
                },
                text = {
                    Text(
                        item.first,

                        color = if (pagerState.currentPage == index) SecondaryDarkColor else SecondaryColor
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) {
            page ->
        when (page) {
            0 -> TabContentScreen(data = "Welcome to Home Screen")
            1 -> TabContentScreen(data = "Welcome to Shopping Screen")
            2 -> TabContentScreen(data = "Welcome to Settings Screen")
        }
    }
}

@Composable
fun TabContentScreen(data: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data,
            style = MaterialTheme.typography.h5,
            color = PrimaryTextColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}