package com.davidrevolt.core.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker


@Composable
fun AppSlider(
    modifier: Modifier = Modifier,
    value: MutableFloatState,
    valueRange: ClosedFloatingPointRange<Float>,
    label: String
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NumberPicker(
            value = value.floatValue.toInt(),
            range = (valueRange.start.toInt())..(valueRange.endInclusive.toInt()),
            onValueChange = {
                value.floatValue = it.toFloat()
            }
        )
        Text(text = label, modifier = Modifier.padding(start = 5.dp, end = 3.dp))
        Slider(
            value = value.floatValue,
            onValueChange = {
                value.floatValue = it
            },
            modifier = Modifier
                .weight(2f),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            valueRange = valueRange
        )
    }

}