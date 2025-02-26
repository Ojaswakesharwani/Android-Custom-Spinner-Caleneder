package com.example.myspinnercalender.wheelspinner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun SelectionView(
    modifier: Modifier = Modifier,
    selectorOptions: SelectorOptions,
    rowOffset: Int,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .weight(rowOffset.toFloat())
                .fillMaxWidth(),
        )


        Column(
            modifier = Modifier
                .weight(1.13f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            //Removed Line to disable line
           Box(
                modifier = Modifier
                    .height(selectorOptions.width)
                    .alpha(selectorOptions.alpha)
                    .background(selectorOptions.color)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .height(selectorOptions.width)
                    .alpha(selectorOptions.alpha)
                    .background(selectorOptions.color)
                    .fillMaxWidth()
            )

        }



        Box(
            modifier = Modifier
                .weight(rowOffset.toFloat())
                .fillMaxWidth(),
        )
    }
}