package com.gstv.primeirocompose.view.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.gstv.primeirocompose.R

@Composable
fun CustomDivider() {
    Divider(
        color = colorResource(id = R.color.black),
        thickness = 2.dp,
        modifier = Modifier.padding(
            top = 40.dp,
            start = 20.dp,
            end = 20.dp,
            bottom = 40.dp
        )
    )
}