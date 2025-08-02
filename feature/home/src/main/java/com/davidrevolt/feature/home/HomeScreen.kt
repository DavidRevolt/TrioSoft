package com.davidrevolt.feature.home

import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davidrevolt.core.designsystem.components.AppFabButton
import com.davidrevolt.core.designsystem.components.AppSlider
import com.davidrevolt.core.designsystem.components.LoadingWheel
import com.davidrevolt.core.designsystem.components.PointColumn
import com.davidrevolt.core.designsystem.drawable.homeBanner
import com.davidrevolt.core.designsystem.icons.AppIcons
import com.davidrevolt.core.designsystem.isSyncing.IsSyncing
import com.davidrevolt.core.designsystem.isSyncing.rememberIsSyncingState
import com.davidrevolt.core.model.Point
import com.davidrevolt.feature.home.chart.PointChart
import java.util.Date
import kotlin.text.Typography.degree


@Composable
fun HomeScreen(
    onSignOutClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val onSaveClick = viewModel::createPoint
    val showCreatePointDialog = remember { mutableStateOf(false) }
    val onMainFabClick = { showCreatePointDialog.value = true }


    if (showCreatePointDialog.value)
        CreatePointDialog(
            showCreatePointDialog = showCreatePointDialog,
            onSaveClick = onSaveClick
        )

    Scaffold(
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppFabButton(
                    onFabClick = onSignOutClick,
                    icon = AppIcons.Logout,
                    containerColor = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                AppFabButton(
                    onFabClick = { viewModel.getWeatherByPlace("rehovot") },
                    icon = AppIcons.Location,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                AppFabButton(onFabClick = onMainFabClick, icon = AppIcons.Add)
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = homeBanner), contentDescription = "home banner",
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(200.dp)
            )
            when (uiState) {
                is HomeUiState.Data -> {
                    val data = (uiState as HomeUiState.Data)
                    if (data.points.isNotEmpty())
                        HomeScreenContent(data.points, data.isSyncing)
                    else
                        Text("Nothing to show here...yet", color = Color.White)
                }

                is HomeUiState.Loading -> LoadingWheel()
            }
        }
    }
}

@Composable
private fun HomeScreenContent(points: List<Point>, isSyncing: Boolean) {

    IsSyncing(
        state = rememberIsSyncingState(isRefreshing = isSyncing),
        onRefresh = {},
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PointChart(points)
            // Cards
            LazyRow(modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)) {
                points.forEach { point ->
                    item {
                        PointColumn(
                            modifier = Modifier.padding(3.dp),
                            date = point.date,
                            temp = point.temperature,
                            humidity = point.humidity,
                            color = Color.Black.copy(alpha = 0.6f)
                        )
                    }
                }
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePointDialog(
    showCreatePointDialog: MutableState<Boolean>,
    onSaveClick: (Int, Int, Date) -> Unit,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        initialDisplayMode = DisplayMode.Input
    )
    val timePickerState = rememberTimePickerState(is24Hour = true)
    val temperatureState = remember { mutableFloatStateOf(0f) }
    val humidityState = remember { mutableFloatStateOf(0f) }
    Dialog(
        onDismissRequest = { showCreatePointDialog.value = false },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Date and Time pickers adapt to SYSTEM LANGUAGE
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.padding(16.dp)
                )

                TimeInput(
                    state = timePickerState,
                    modifier = Modifier.padding(16.dp)
                )


                AppSlider(
                    modifier = Modifier.padding(5.dp),
                    value = temperatureState,
                    -10f..50f,
                    label = "C$degree"
                )
                AppSlider(
                    modifier = Modifier.padding(5.dp),
                    value = humidityState,
                    valueRange = 0f..100f,
                    label = "HUM%"
                )

                // set Date, to get the date obj use calendar.time
                val calendar = Calendar.getInstance()
                calendar.time =
                    if (datePickerState.selectedDateMillis != null) Date(datePickerState.selectedDateMillis!!) else Date()
                calendar.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                calendar.set(Calendar.MINUTE, timePickerState.minute)

                Button(onClick = {
                    onSaveClick(
                        humidityState.floatValue.toInt(),
                        temperatureState.floatValue.toInt(),
                        calendar.time
                    )
                    showCreatePointDialog.value = false
                }) {
                    Text("Save")
                }
            }
        }
    }
}