package com.example.examateapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.examateapp.data.local.Constants.HUGE_MARGIN
import com.example.examateapp.data.local.Constants.LARGE_MARGIN
import com.example.examateapp.data.local.Constants.MEDIUM_MARGIN
import com.example.examateapp.data.local.Constants.SMALL_MARGIN
import com.example.examateapp.ui.theme.Turquoise
import com.example.examateapp.ui.theme.TurquoiseBold
import com.example.examateapp.util.studyPlanData

@Preview(showSystemUi = true)
@Composable
fun HomeScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(bottom = 130.dp)
    ) {
        item {
            HomeHeader()
        }

        itemsIndexed(studyPlanData()) { index , item ->
            val isLastItem = index == studyPlanData().size-1
            ListItemStudy(item,isLastItem)
        }
    }
}

@Composable
fun HomeHeader() {

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (homeText, hiText, usernameText, notificationIcon) = createRefs()

            Text(
                modifier = Modifier.constrainAs(homeText) {
                    top.linkTo(parent.top, BIG_MARGIN)
                    start.linkTo(parent.start, LARGE_MARGIN)
                },
                color = Turquoise,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.home),
                fontSize = 28.sp
            )

            Text(
                modifier = Modifier.constrainAs(hiText) {
                    top.linkTo(homeText.bottom, MEDIUM_MARGIN)
                    start.linkTo(parent.start, LARGE_MARGIN)
                },
                color = Color.Black,
                text = stringResource(id = R.string.hi),
                fontSize = 23.sp
            )

            Text(
                modifier = Modifier.constrainAs(usernameText) {
                    top.linkTo(homeText.bottom, MEDIUM_MARGIN)
                    start.linkTo(hiText.end, SMALL_MARGIN)
                },
                color = TurquoiseBold,
                fontWeight = FontWeight.Bold,
                text = "User Name",
                fontSize = 23.sp
            )

            Icon(
                modifier = Modifier.constrainAs(notificationIcon) {
                    top.linkTo(homeText.top)
                    bottom.linkTo(homeText.bottom)
                    end.linkTo(parent.end, LARGE_MARGIN)
                },
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Notification Icon",
                tint = Turquoise
            )
        }
        Spacer(modifier = Modifier.padding(top = LARGE_MARGIN))
        Text(
            modifier = Modifier.padding(start = LARGE_MARGIN),
            color = TurquoiseBold.copy(alpha = 0.9f),
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.study_plan),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.padding(top = LARGE_MARGIN))
    }
}
