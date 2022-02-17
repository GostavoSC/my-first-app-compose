package com.gstv.primeirocompose.view.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gstv.primeirocompose.R

@Composable
fun GenericAppBar(
    text: String = "Login",
    backgroundColor: Int = R.color.white,
    textColor: Int = R.color.blue_base
) {
    TopAppBar(
        title = {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val title = createRef()
                Text(
                    modifier = Modifier.constrainAs(title) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                    text = text,
                    color = colorResource(id = textColor)
                )

            }
        },
        backgroundColor = colorResource(id = backgroundColor),
        elevation = 0.dp
    )

}