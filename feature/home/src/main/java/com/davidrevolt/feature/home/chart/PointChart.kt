package com.davidrevolt.feature.home.chart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.davidrevolt.core.model.Point
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.composed.plus
import com.patrykandpatrick.vico.core.entry.composed.ComposedChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun PointChart(points: List<Point>) {
    val dateFormat = SimpleDateFormat("HH:mm MMM d, yyy", Locale.US)

    val temperatureList = points.mapIndexed { index, point ->entryOf(index.toFloat(),point.temperature.toFloat()) }
    val humidityList = points.mapIndexed { index, point ->entryOf(index.toFloat(),point.humidity.toFloat()) }
    val composedChartEntryModelProducer = ComposedChartEntryModelProducer.build {
        add(temperatureList)
        add(humidityList)
    }


    val bottomAxisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
        dateFormat.format(points[value.toInt()].date)
    }
    val startAxisValueFormatter = AxisValueFormatter<AxisPosition.Vertical.Start> { value, _ ->
        value.toInt().toString()
    }



    val chartStyle = m3ChartStyle(axisLineColor = Color.White, axisLabelColor = Color.White, entityColors = listOf(Color(0xffb983ff),Color(0xff91b1fd)))
    ProvideChartStyle(chartStyle = chartStyle){
        val temperatureChart = lineChart()
        val humidityChart = lineChart()

        humidityChart.lines= listOf(chartStyle.lineChart.lines[1])
        Chart(
            chart = remember(temperatureChart, humidityChart) { temperatureChart + humidityChart },
            chartModelProducer = composedChartEntryModelProducer,
            startAxis = rememberStartAxis(valueFormatter = startAxisValueFormatter),
            bottomAxis = rememberBottomAxis(valueFormatter = bottomAxisValueFormatter),
            marker = rememberMarker(),
            legend = rememberLegend(listOf("Temperature","Humidity"))
        )
    }
}
