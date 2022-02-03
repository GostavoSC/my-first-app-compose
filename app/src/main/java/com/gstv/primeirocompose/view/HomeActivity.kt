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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.gstv.primeirocompose.R
import com.gstv.primeirocompose.view.composables.BodyItemButton
import com.gstv.primeirocompose.view.composables.HeadLayout
import com.gstv.primeirocompose.view.composables.WelcomeMessage

class HomeActivity : AppCompatActivity() {
    private var account: GoogleSignInAccount? = null

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUserAccount()
        setContent {
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
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background))
        ) {

            val (head, message, body) = createRefs()
            Box(modifier = Modifier
                .constrainAs(head) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .wrapContentHeight()) {
                HeadLayout()
            }

            Column(
                modifier = Modifier
                    .padding(top = 15.dp, end = 30.dp, start = 30.dp)
                    .constrainAs(message) {
                        top.linkTo(parent.top, margin = 15.dp)
                    }
                    .fillMaxWidth()
                    .wrapContentHeight()

            ) {
                WelcomeMessage(account)
            }
            LazyVerticalGrid(
                modifier = Modifier.constrainAs(body) {
                    top.linkTo(message.bottom, margin = 20.dp)
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
                            "Your product or page here",
                            "your desc about here"
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
                            "Your product or page here",
                            "your desc about here"
                        )
                    }
                })
        }
    }


}