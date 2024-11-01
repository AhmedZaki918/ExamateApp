package com.example.examateapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.examateapp.data.local.Constants.LARGE_MARGIN
import com.example.examateapp.data.local.Constants.MEDIUM_MARGIN
import com.example.examateapp.data.model.StudyPlan
import com.example.examateapp.ui.theme.GrayLight
import com.example.examateapp.ui.theme.GrayMedium
import com.example.examateapp.ui.theme.Turquoise
import com.example.examateapp.ui.theme.TurquoiseLight
import com.example.examateapp.ui.theme.TurquoiseMedium
import com.example.examateapp.ui.theme.TurquoiseZero

@Composable
fun ListItemStudy(
    currentItem: StudyPlan,
    isLastItem: Boolean
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (circleBox, captionText) = createRefs()


        Circle(
            modifier = Modifier.constrainAs(circleBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            value = currentItem.planNumber,
            isCircleActive = currentItem.isItemActive,
            isLastCircle = isLastItem
        )


        val annotatedText: AnnotatedString = buildAnnotatedString {
            append(currentItem.caption.take(15))
            if (currentItem.caption.length > 15) {
                append("\n")
                append(currentItem.caption.substring(15))
            }
        }

        Text(
            modifier = Modifier.constrainAs(captionText) {
                top.linkTo(circleBox.top)
                bottom.linkTo(circleBox.bottom, MEDIUM_MARGIN)
                start.linkTo(circleBox.end, MEDIUM_MARGIN)
            },
            color = if (currentItem.isItemActive) Turquoise else Color.Gray,
            fontWeight = FontWeight.Bold,
            text = annotatedText,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp
        )
    }
}


@Composable
fun Circle(
    modifier: Modifier,
    value: String,
    isCircleActive: Boolean = true,
    isLastCircle: Boolean = false
) {

    ConstraintLayout(modifier = modifier.wrapContentSize()) {
        val (outerBox, innerBox, lineBox, lockBox) = createRefs()

        // Outer Circle
        Box(
            modifier = Modifier
                .constrainAs(outerBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(start = LARGE_MARGIN)
                .size(100.dp)
                .clip(CircleShape)
                .border(
                    width = 6.dp,
                    color = if (isCircleActive) TurquoiseMedium else GrayMedium,
                    shape = CircleShape
                )
        )

        // Inner Circle
        Box(
            modifier = Modifier
                .constrainAs(innerBox) {
                    start.linkTo(outerBox.start)
                    end.linkTo(outerBox.end)
                    top.linkTo(outerBox.top)
                    bottom.linkTo(outerBox.bottom)
                }
                .padding(start = LARGE_MARGIN)
                .size(65.dp)
                .clip(CircleShape)
                .background(color = if (isCircleActive) TurquoiseZero else GrayMedium)
                .border(
                    width = if (isCircleActive) 3.dp else 0.dp,
                    color = if (isCircleActive) TurquoiseLight else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = if (isCircleActive) TurquoiseMedium else GrayLight,
                fontWeight = FontWeight.Bold,
                text = value,
                fontSize = 35.sp
            )
        }



        val lineBackground = if (!isLastCircle) {
            if (isCircleActive) TurquoiseLight.copy(alpha = 0.3f) else GrayMedium
        } else Color.Transparent

        // Line Separator
        Box(
            modifier = Modifier
                .constrainAs(lineBox) {
                    top.linkTo(outerBox.bottom)
                    start.linkTo(outerBox.start, LARGE_MARGIN)
                    end.linkTo(outerBox.end)
                }
                .size(width = 14.dp, height = 37.dp)
                .background(lineBackground)
        )


        // Lock
        if (!isCircleActive) {
            Box(
                modifier = Modifier
                    .constrainAs(lockBox) {
                        start.linkTo(lineBox.end, 12.dp)
                        bottom.linkTo(lineBox.top)
                    }
                    .size(27.dp)
                    .clip(CircleShape)
                    .background(GrayMedium),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Notification Icon",
                    tint = GrayLight
                )
            }
        }
    }
}