package com.gstv.primeirocompose.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.gstv.primeirocompose.R
import com.gstv.primeirocompose.view.composables.BodyItemButton
import com.gstv.primeirocompose.view.composables.HeadLayout
import com.gstv.primeirocompose.view.composables.LoginAppBar
import com.gstv.primeirocompose.view.composables.WelcomeMessage

class HomeActivity : AppCompatActivity() {
    private var account: GoogleSignInAccount? = null

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUserAccount()
        setContent {
            LoginAppBar()
            SetupHomeScreen()
        }
    }

    private fun setupUserAccount() {
        if (intent.hasExtra("account")) {
            intent.extras?.get("account")?.let {
                account = (it as GoogleSignInAccount)
            }
        }
    }

    @ExperimentalFoundationApi
    @Preview
    @Composable
    fun SetupHomeScreen() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .background(color = colorResource(id = R.color.background))
        ) {

            val (head, message, backgroundHead, body) = createRefs()
            Box(modifier = Modifier
                .constrainAs(head) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .wrapContentHeight()) {
                LoginAppBar(
                    text = "Home Page",
                    backgroundColor = R.color.blue_base,
                    textColor = R.color.white
                )
            }

            Box(
                modifier = Modifier
                    .sizeIn(minHeight = 70.dp)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.blue_base))
                    .constrainAs(backgroundHead) {
                        top.linkTo(head.bottom)
                    }
            ) {

            }

            Column(
                modifier = Modifier
                    .padding(top = 10.dp, end = 30.dp, start = 30.dp)
                    .constrainAs(message) {
                        top.linkTo(head.bottom, margin = 10.dp)
                    }
                    .fillMaxWidth()
                    .wrapContentHeight()

            ) {
                account?.let {

                    it.givenName?.let { name ->
                        WelcomeMessage(name = name, it.photoUrl)
                    }
                }
            }
            LazyVerticalGrid(
                modifier = Modifier.constrainAs(body) {
                    top.linkTo(message.bottom, margin = 110.dp)
                },
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(10.dp),
                content = {
                    item {
                        BodyItemButton(
                            onClick = {
                                Toast.makeText(
                                    this@HomeActivity,
                                    "Product one",
                                    Toast.LENGTH_LONG
                                ).show()
                            },
                            "Nosso Clube",
                        )
                    }
                    item {
                        BodyItemButton(
                            onClick = {
                                Toast.makeText(
                                    this@HomeActivity,
                                    "Product two",
                                    Toast.LENGTH_LONG
                                ).show()
                            },
                            "Consultas"
                        )
                    }
                })
        }
    }


}