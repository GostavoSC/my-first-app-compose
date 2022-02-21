package com.gstv.primeirocompose.view.composables

import EmailField
import GenericButton
import GenericField
import PasswordField
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.gson.Gson
import com.gstv.primeirocompose.R
import com.gstv.primeirocompose.model.Account


@Composable
fun MainCreateUserContent(navController: NavHostController) {
    FormNewUser(navController)
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun FormNewUser(navController: NavHostController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .statusBarsPadding()
            .navigationBarsWithImePadding()
    ) {
        val (forms, button, appBar) = createRefs()

        val emailState = remember {
            mutableStateOf("")
        }
        val nameState = remember {
            mutableStateOf("")
        }
        val passwordState = remember {
            mutableStateOf("")
        }
        val confirmPasswordState = remember {
            mutableStateOf("")
        }


        Box(modifier = Modifier
            .wrapContentSize()
            .constrainAs(appBar) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)

            }) {

            GenericAppBar(text = "Cadastro")
        }

        Column(modifier = Modifier
            .wrapContentSize()
            .constrainAs(forms) {
                top.linkTo(appBar.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)

            }) {
            GenericField(textState = nameState)
            EmailField(emailState = emailState)
            PasswordField(
                passwordState = passwordState,
                hint = "Crie sua senha"
            )
            PasswordField(
                passwordState = confirmPasswordState,
                hint = "Confirme sua senha",
                isConfirmPasswordField = true,
                passwordValue = passwordState.value
            )

        }
        Box(modifier = Modifier.constrainAs(button) {
            top.linkTo(forms.bottom, margin = 15.dp)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        }) {
            GenericButton(
                {
                    if (!verifyIfIsEmpty(
                            passwordState.value,
                            confirmPasswordState.value,
                            emailState.value,
                            nameState.value
                        )
                    ) {
                        navController.navigate("home")
                        navController.getBackStackEntry("home").arguments?.putString(
                            "account",
                            Gson().toJson(
                                Account(
                                    nameState.value,
                                    emailState.value,
                                    passwordState.value,
                                    photo = "semfoto"
                                )
                            )
                        )
                    }
                },
                "Cadastrar",
            )
        }
    }
}

fun verifyIfIsEmpty(value: String, value1: String, value2: String, value3: String): Boolean {
    return value.isEmpty() || value1.isEmpty() || value2.isEmpty() || value3.isEmpty()
}

