@file:Suppress("DEPRECATION")

package com.example.myspinnercalender.compose.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myspinnercalender.compose.component.SelectorView
import com.example.myspinnercalender.ui.themes.PickerTheme
import com.example.myspinnercalender.ui.themes.colorLightPrimary
import com.example.myspinnercalender.ui.themes.colorLightTextPrimary
import com.example.myspinnercalender.ui.themes.lightPallet
import com.example.myspinnercalender.utils.DateUtils
import com.example.myspinnercalender.utils.daysOfDate
import com.example.myspinnercalender.utils.monthsOfDate
import com.example.myspinnercalender.utils.withDay
import com.example.myspinnercalender.utils.withMonth
import com.example.myspinnercalender.utils.withYear
import com.example.myspinnercalender.wheelspinner.InfiniteWheelView
import com.example.myspinnercalender.wheelspinner.SelectorOptions
import java.text.DateFormatSymbols
import java.util.Date

@Composable
fun WheelDatePicker(
    offset: Int = 4,
    yearsRange: IntRange = IntRange(1990, 2021),
    startDate: Date = Date(DateUtils.getCurrentTime()),
    textSize: Int = 16,
    selectorEffectEnabled: Boolean = true,
    onDateChanged: (Int, Int, Int, Long) -> Unit = { _, _, _, _ -> },
    darkModeEnabled: Boolean = true,
) {
    var selectedDate by remember { mutableStateOf(startDate) }

    val months = selectedDate.monthsOfDate()

    val days = selectedDate.daysOfDate()

    val years = mutableListOf<Int>().apply {
        for (year in yearsRange) {
            add(year)
        }
    }

    LaunchedEffect(selectedDate) {
        onDateChanged(selectedDate.day, selectedDate.month, selectedDate.year,
            selectedDate.date.toLong()
        )
    }

    val fontSize = maxOf(13, minOf(19, textSize))


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(if (darkModeEnabled) PickerTheme.colors.primary else colorLightPrimary),
        contentAlignment = Alignment.Center
    ) {

        val height = (fontSize + 11).dp

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
        ) {


            key(days.size) {

                //MONTH VIEW
                InfiniteWheelView(
                    modifier = Modifier.weight(2f),
                    itemSize = DpSize(150.dp, height),
                    selection = maxOf(months.indexOf(selectedDate.month), 0),
                    itemCount = months.size,
                    rowOffset = offset,
                    selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                    onFocusItem = {
                        selectedDate = selectedDate.withMonth(months[it])
                    },
                    content = {
                        Text(
                            text = DateFormatSymbols().months[months[it]],
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .width(120.dp),
                            fontSize = fontSize.sp,
                            color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                        )
                    })

                // DATE VIEW
                InfiniteWheelView(modifier = Modifier.weight(1f),
                    itemSize = DpSize(150.dp, height),
                    selection = maxOf(days.indexOf(selectedDate.day), 0),
                    itemCount = days.size,
                    rowOffset = offset,
                    selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                    onFocusItem = {
                        selectedDate = selectedDate.withDay(days[it])
                    },
                    content = {
                        Text(
                            text = days[it].toString(),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .width(50.dp),
                            fontSize = fontSize.sp,
                            color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                        )
                    })
            }




            //YEAR VIEW
            InfiniteWheelView(modifier = Modifier.weight(1f),
                itemSize = DpSize(150.dp, height),
                selection = years.indexOf(selectedDate.year),
                itemCount = years.size,
                rowOffset = offset,
                isEndless = years.size > offset * 2,
                selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                onFocusItem = {
                    selectedDate = selectedDate.withYear(years[it])
                },
                content = {
                    Text(
                        text = years[it].toString(),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .width(100.dp),
                        fontSize = fontSize.sp,
                        color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                    )
                })
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (darkModeEnabled) PickerTheme.pallets else lightPallet
                    )
                ),
        ) {}

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.LightGray.copy(alpha = 0.3f), shape = RoundedCornerShape(12.dp))
        )

        SelectorView(darkModeEnabled= darkModeEnabled, offset = offset)



    }


}


@Preview
@Composable
fun DatePickerPreview() {
    WheelDatePicker(onDateChanged = { _, _, _, _ -> })
}