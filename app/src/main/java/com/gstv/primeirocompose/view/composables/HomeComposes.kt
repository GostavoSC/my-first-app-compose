package com.gstv.primeirocompose.view.composables

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gstv.primeirocompose.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WelcomeMessage(name: String, uri: Uri?) {
    Column(
        modifier = Modifier
            .background(
                color = colorResource(
                    id = R.color.background
                ),
                shape = RoundedCornerShape(5.dp)
            )
            .fillMaxWidth()
            .padding(all = 10.dp)
            .wrapContentHeight()

    ) {
        ConstraintLayout(modifier = Modifier.wrapContentSize()) {
            val (image, columnInfo) = createRefs()

            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(60.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            ) {
                GlideImage(
                    imageModel = uri,
                    contentDescription = "userImage"
                )
            }

            Column(modifier = Modifier
                .padding(top = 5.dp, start = 10.dp)
                .constrainAs(columnInfo) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end)
                }) {
                Text(
                    text = name,
                    color = Color.Black,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp
                )
            }

        }
    }
}

@Composable
fun HeadLayout() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(
                color = colorResource(
                    id = R.color.black
                )
            )
    ) {
        Spacer(modifier = Modifier.padding(30.dp))

    }
}

@Preview
@Composable
fun BodyButtons() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()

    ) {
//        BodyItemButton(onClick = { /*TODO*/ })
    }
}


@Composable
fun BodyItemButton(
    onClick: () -> Unit,
    nameItem: String
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .background(color = Color.Transparent)
            .fillMaxWidth(), contentAlignment = Alignment.Center

    ) {
        Row(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.blue_base),
                    shape = RoundedCornerShape(3.dp)
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onClick()
                    },
                contentAlignment = Alignment.Center,


                ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = nameItem,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

            }
        }
    }
}