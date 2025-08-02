package com.davidrevolt.core.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.text.Typography.degree

val dividerColor = Color.White.copy(alpha = 0.5f)
val containerColor = Color(0f, 0f, 0f, 0.3f)

val dayMonthStyle = TextStyle(
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Light,
    color = Color.White,
    fontSize = 20.sp,
)

val yearStyle = TextStyle(
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Light,
    color = Color.LightGray,
    fontSize = 16.sp,
)

val tempAndHumidityStyle = TextStyle(
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Bold,
    textDirection = TextDirection.Ltr,
    color = Color.White,
    fontSize = 20.sp,
)

val timeFormat = SimpleDateFormat("HH:mm", Locale.US)
val dateFormat = SimpleDateFormat("MMM d, yyy", Locale.US)

@Composable
fun PointColumn(
    modifier: Modifier = Modifier,
    date: Date,
    temp: Int,
    humidity: Int,
    color: Color = containerColor
) {
    Card(
        modifier = modifier
            .size(
                width = 120.dp,
                height = 140.dp
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dateFormat.format(date),
                    style = yearStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = timeFormat.format(date),
                    style = dayMonthStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Divider(color = dividerColor )
            }

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$temp$degree | $humidity%",
                    style = tempAndHumidityStyle ,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                )
            }
        }
    }
}

