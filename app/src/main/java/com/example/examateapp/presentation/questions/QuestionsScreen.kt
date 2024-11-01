package com.example.examateapp.presentation.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.examateapp.data.local.Constants.SMALL_MARGIN
import com.example.examateapp.data.local.Constants.TINY_MARGIN
import com.example.examateapp.ui.theme.Turquoise
import com.example.examateapp.ui.theme.TurquoiseBold
import com.example.examateapp.ui.theme.TurquoiseLight
import com.example.examateapp.util.questionsData
import com.example.examateapp.util.tabItems
import com.example.examateapp.util.tabQuestionsItems

@Preview(showSystemUi = true)
@Composable
fun QuestionsScreen() {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val (titleText, viewAsText, tabsColumn) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(titleText) {
                    top.linkTo(parent.top, BIG_MARGIN)
                    start.linkTo(parent.start, LARGE_MARGIN)
                },
            color = Turquoise,
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.questions),
            fontSize = 28.sp
        )


        Row(
            modifier = Modifier
                .constrainAs(viewAsText) {
                    top.linkTo(titleText.top)
                    bottom.linkTo(titleText.bottom)
                    end.linkTo(parent.end, LARGE_MARGIN)
                }
                .background(TurquoiseLight.copy(alpha = 0.3f))
                .padding(TINY_MARGIN)
        ) {

            Text(
                color = TurquoiseBold,
                text = "view As: Categories",
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.width(TINY_MARGIN))

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .padding(top = TINY_MARGIN),
                contentDescription = "",
                imageVector = Icons.Default.KeyboardArrowDown,
                tint = TurquoiseBold
            )
        }



        TabsContent(modifier = Modifier
            .constrainAs(tabsColumn) {
                top.linkTo(titleText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(top = SMALL_MARGIN))
    }
}

@Composable
fun TabsContent(modifier: Modifier) {

    Column(modifier = modifier) {

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


        TabRow(
            modifier = Modifier.padding(end = 100.dp),
            selectedTabIndex = selectedTabIndex,
            divider = {},
            containerColor = Color.Transparent
        ) {
            tabQuestionsItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Row(modifier = Modifier.wrapContentSize()) {

                            Icon(
                                contentDescription = "",
                                painter = painterResource(id = item.icon)
                            )
                            Spacer(modifier = Modifier.width(SMALL_MARGIN))
                            Text(text = item.title)
                        }
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
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                if (index == 0) {
                    FilterButton()
                    Writing()
                }
            }
        }
    }
}

@Composable
fun Writing() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(all = SMALL_MARGIN)
    ) {
        items(questionsData()) {
            ListItemQuestion(it)
        }
    }
}

@Composable
fun FilterButton() {
    Button(
        modifier = Modifier
            .padding(
                start = LARGE_MARGIN,
                top = MEDIUM_MARGIN
            )
            .fillMaxWidth(0.27f),
        onClick = {},
        colors = ButtonColors(
            contentColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
            containerColor = TurquoiseLight.copy(alpha = 0.3f),
            disabledContainerColor = Color.Transparent
        ),
        shape = RectangleShape
    ) {
        Text(
            text = "Filter ",
            color = TurquoiseBold
        )

        Icon(
            tint = TurquoiseBold,
            contentDescription = "",
            painter = painterResource(id = R.drawable.ic_filter)
        )
    }
}