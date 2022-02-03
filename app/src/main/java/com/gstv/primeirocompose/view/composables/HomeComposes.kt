package com.gstv.primeirocompose.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.gstv.primeirocompose.R

@Composable
fun WelcomeMessage(account: GoogleSignInAccount?) {
    account?.let {
        it.givenName?.let { name ->
            Text(
                text = "Welcome,\n$name!",
                fontWeight = FontWeight.W500,
                fontSize = 17.sp,
                color = Color.White
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                color = colorResource(
                    id = R.color.background
                ),
                shape = RoundedCornerShape(5.dp)
            )
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentHeight()
    ) {
        Text(
            text = "Your app name",
            color = Color.Black,
            fontWeight = FontWeight.W500,
            fontSize = 30.sp,
            modifier = Modifier.padding(
                top = 15.dp,
                bottom = 15.dp
            )
        )
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
        Spacer(modifier = Modifier.padding(70.dp))

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
    nameItem: String,
    subTitleItem: String
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .background(color = Color.Transparent)
    ) {
        Row(modifier = Modifier.background(Color.Black, shape = RoundedCornerShape(3.dp))) {

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onClick()
                    }

            ) {
                Column {
                    Text(
                        text = nameItem,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Text(
                        text = subTitleItem,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

            }
        }
    }
}