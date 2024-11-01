package com.example.examateapp.presentation.connector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.examateapp.R
import com.example.examateapp.data.local.Constants.LARGE_MARGIN
import com.example.examateapp.data.local.Constants.MEDIUM_MARGIN
import com.example.examateapp.data.local.Constants.SMALL_MARGIN
import com.example.examateapp.data.local.Constants.TINY_MARGIN
import com.example.examateapp.data.model.Suggestions
import com.example.examateapp.ui.theme.GrayLight
import com.example.examateapp.ui.theme.GrayMedium
import com.example.examateapp.ui.theme.Turquoise
import com.example.examateapp.ui.theme.TurquoiseBold
import com.example.examateapp.ui.theme.TurquoiseLight

@Composable
fun ListItemConnect(currentItem: Suggestions) {

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = MEDIUM_MARGIN, vertical = TINY_MARGIN),
        colors = CardColors(
            contentColor = Color.Transparent,
            containerColor = GrayMedium.copy(alpha = 0.3f),
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {

        ConstraintLayout(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(vertical = MEDIUM_MARGIN)
        ) {
            val (profileBox, instructorText, lastSeenText,
                targetLevelText, subjectRow, infoConstraintLayout
            ) = createRefs()


            Box(
                modifier = Modifier
                    .constrainAs(profileBox) {
                        start.linkTo(parent.start, LARGE_MARGIN)
                        top.linkTo(parent.top, SMALL_MARGIN)
                    }
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Turquoise),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = GrayLight,
                    text = currentItem.profile,
                    fontSize = 20.sp
                )
            }


            Text(
                modifier = Modifier
                    .constrainAs(instructorText) {
                        start.linkTo(profileBox.end, MEDIUM_MARGIN)
                        top.linkTo(parent.top)
                    }.fillMaxWidth(0.35f),
                color = TurquoiseBold.copy(alpha = 0.9f),
                fontWeight = FontWeight.Bold,
                text = currentItem.instructor,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )


            Text(
                modifier = Modifier
                    .constrainAs(lastSeenText) {
                        start.linkTo(profileBox.end, MEDIUM_MARGIN)
                        top.linkTo(instructorText.bottom, 4.dp)
                    },
                fontSize = 14.sp,
                color = Color.Gray,
                text = "Last seen online: ${currentItem.lastSeen}",
            )

            Box(
                modifier = Modifier
                    .constrainAs(targetLevelText) {
                        top.linkTo(instructorText.top)
                        bottom.linkTo(instructorText.bottom)
                        start.linkTo(instructorText.end, SMALL_MARGIN)
                    }
                    .background(Turquoise)
            ) {
                Text(
                    color = Color.White,
                    text = "Targeting: ${currentItem.targetLevel}",
                    fontSize = 14.sp
                )
            }

            // Subjects
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(subjectRow) {
                        start.linkTo(lastSeenText.start)
                        top.linkTo(lastSeenText.bottom)
                    }) {


                for (x in currentItem.subjects.indices) {
                    Box(
                        modifier = Modifier
                            .background(TurquoiseLight.copy(alpha = 0.3f))
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = TINY_MARGIN),
                            color = Color.Black,
                            fontSize = 12.sp,
                            text = currentItem.subjects[x],
                        )
                    }
                    Spacer(modifier = Modifier.width(SMALL_MARGIN))
                }
            }


            //  location - gender - age - date
            ConstraintLayout(modifier = Modifier
                .constrainAs(infoConstraintLayout) {
                    top.linkTo(subjectRow.bottom, MEDIUM_MARGIN)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .wrapContentHeight()
            ) {

                val (locationIcon, genderIcon, ageIcon, dateIcon,
                    locationText, genderText, ageText, dateText
                ) = createRefs()


                Icon(
                    modifier = Modifier.constrainAs(locationIcon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, LARGE_MARGIN)
                    },
                    tint = Color.Gray,
                    contentDescription = "location",
                    painter = painterResource(id = R.drawable.ic_location)
                )

                Text(
                    modifier = Modifier.constrainAs(locationText) {
                        start.linkTo(locationIcon.end, TINY_MARGIN)
                        top.linkTo(locationIcon.top)
                        bottom.linkTo(locationIcon.bottom)
                    },
                    text = currentItem.info.location, color = Color.Gray,
                    fontSize = 12.sp,
                )


                Icon(
                    modifier = Modifier.constrainAs(genderIcon) {
                        top.linkTo(parent.top)
                        start.linkTo(locationText.end, LARGE_MARGIN)
                    },
                    tint = Color.Gray,
                    contentDescription = "gender",
                    painter = painterResource(id = R.drawable.ic_female)
                )

                Text(
                    modifier = Modifier.constrainAs(genderText) {
                        start.linkTo(genderIcon.end, TINY_MARGIN)
                        top.linkTo(genderIcon.top)
                        bottom.linkTo(genderIcon.bottom)
                    },
                    text = currentItem.info.gender, color = Color.Gray,
                    fontSize = 12.sp,
                )


                Icon(
                    modifier = Modifier.constrainAs(ageIcon) {
                        top.linkTo(parent.top)
                        start.linkTo(genderText.end, LARGE_MARGIN)
                    },
                    tint = Color.Gray,
                    contentDescription = "age",
                    painter = painterResource(id = R.drawable.ic_birthday)
                )

                Text(
                    modifier = Modifier.constrainAs(ageText) {
                        start.linkTo(ageIcon.end, TINY_MARGIN)
                        top.linkTo(ageIcon.top)
                        bottom.linkTo(ageIcon.bottom)
                    },
                    text = currentItem.info.age, color = Color.Gray,
                    fontSize = 12.sp,
                )

                Icon(
                    modifier = Modifier.constrainAs(dateIcon) {
                        top.linkTo(parent.top)
                        start.linkTo(ageText.end, LARGE_MARGIN)
                    },
                    tint = Color.Gray,
                    contentDescription = "date",
                    painter = painterResource(id = R.drawable.ic_calendar)
                )

                Text(
                    modifier = Modifier.constrainAs(dateText) {
                        start.linkTo(dateIcon.end, TINY_MARGIN)
                        top.linkTo(dateIcon.top)
                        bottom.linkTo(dateIcon.bottom)
                    },
                    text = currentItem.info.date, color = Color.Gray,
                    fontSize = 12.sp,
                )
            }
        }
    }
}