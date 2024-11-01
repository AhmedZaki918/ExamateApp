package com.example.examateapp.presentation.connector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.examateapp.R
import com.example.examateapp.data.local.Constants.BIG_MARGIN
import com.example.examateapp.data.local.Constants.LARGE_MARGIN
import com.example.examateapp.data.local.Constants.MEDIUM_MARGIN
import com.example.examateapp.ui.theme.Turquoise
import com.example.examateapp.ui.theme.TurquoiseBold
import com.example.examateapp.util.suggestionsPartners
import com.example.examateapp.util.tabItems


@Preview(showSystemUi = true)
@Composable
fun ConnectorScreen() {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier.padding(
                top = BIG_MARGIN,
                start = LARGE_MARGIN
            ),
            color = Turquoise,
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.connect),
            fontSize = 28.sp
        )


        TabRow(
            modifier = Modifier.padding(horizontal = MEDIUM_MARGIN, vertical = MEDIUM_MARGIN),
            selectedTabIndex = selectedTabIndex,
            divider = {},
            containerColor = Color.Transparent
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(text = item.title)
                    },
                    selectedContentColor = Turquoise,
                    unselectedContentColor = Color.Gray
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Suggestions content
                if (index == 0) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentPadding = PaddingValues(bottom = 130.dp)
                    ) {
                        item {

                            ConstraintLayout(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                val (titleText, filterIcon) = createRefs()

                                Text(
                                    modifier = Modifier
                                        .constrainAs(titleText) {
                                            start.linkTo(parent.start)
                                            top.linkTo(parent.top)
                                        }
                                        .padding(start = LARGE_MARGIN, top = MEDIUM_MARGIN),
                                    color = TurquoiseBold.copy(alpha = 0.9f),
                                    fontWeight = FontWeight.Bold,
                                    text = stringResource(id = R.string.suggested_plan) + ":",
                                    fontSize = 16.sp
                                )

                                Icon(
                                    modifier = Modifier
                                        .constrainAs(filterIcon) {
                                            top.linkTo(titleText.top)
                                            bottom.linkTo(titleText.bottom)
                                            end.linkTo(parent.end)
                                        }
                                        .padding(end = LARGE_MARGIN, top = MEDIUM_MARGIN),
                                    contentDescription = "filter",
                                    painter = painterResource(id = R.drawable.ic_filter),
                                    tint = Turquoise
                                )
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.padding(top = LARGE_MARGIN))
                        }

                        items(suggestionsPartners()) {
                            ListItemConnect(it)
                        }
                    }

                    // Chat content
                } else {
                    Text(text = "Chat")
                }
            }
        }
    }
}
