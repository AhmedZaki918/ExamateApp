package com.example.examateapp.presentation.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.examateapp.data.local.Constants.MEDIUM_MARGIN
import com.example.examateapp.data.local.Constants.SMALL_MARGIN
import com.example.examateapp.data.local.Constants.TINY_MARGIN
import com.example.examateapp.data.model.Questions
import com.example.examateapp.ui.theme.GrayHigh
import com.example.examateapp.ui.theme.GrayMedium
import com.example.examateapp.ui.theme.Turquoise
import com.example.examateapp.ui.theme.TurquoiseBold
import com.example.examateapp.ui.theme.TurquoiseLight

@Composable
fun ListItemQuestion(currentItem: Questions) {

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = TINY_MARGIN, vertical = TINY_MARGIN),
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
                .padding(bottom = MEDIUM_MARGIN, start = MEDIUM_MARGIN, end = MEDIUM_MARGIN)
        ) {

            val (titleText, imageIcon, captionText, progressText, barBox) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(titleText) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top, MEDIUM_MARGIN)
                    }
                    .background(TurquoiseLight.copy(alpha = 0.3f)),
                text = "${currentItem.questionsDone} from 10 Questions",
                color = TurquoiseBold,
                fontSize = 12.sp
            )

            Icon(
                modifier = Modifier.constrainAs(imageIcon) {
                    top.linkTo(titleText.bottom, SMALL_MARGIN)
                    start.linkTo(titleText.start)
                },
                contentDescription = "",
                painter = painterResource(id = currentItem.icon),
                tint = TurquoiseBold
            )

            Text(
                modifier = Modifier
                    .constrainAs(captionText) {
                        start.linkTo(imageIcon.end, SMALL_MARGIN)
                        top.linkTo(imageIcon.top)
                        bottom.linkTo(imageIcon.bottom)
                    },
                text = currentItem.caption,
                color = GrayHigh,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier
                    .constrainAs(progressText) {
                        start.linkTo(imageIcon.start)
                        top.linkTo(captionText.bottom, SMALL_MARGIN)
                    },
                text = "Progress ${currentItem.progressText}%",
                color = GrayHigh.copy(alpha = 0.8f),
                fontSize = 12.sp
            )


            ProgressBar(
                modifier = Modifier
                    .constrainAs(barBox) {
                        top.linkTo(progressText.bottom, SMALL_MARGIN)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                currentItem.progressBar
            )
        }
    }
}


@Composable
fun ProgressBar(
    modifier: Modifier,
    progress: Float
) {
    val percentage by remember { mutableFloatStateOf(progress) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator(
            progress = { percentage },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            color = Turquoise,
            trackColor = GrayMedium.copy(alpha = 0.5f),
        )
    }
}
