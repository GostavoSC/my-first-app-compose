package com.gstv.primeirocompose.view.composables

import EmailField
import GenericButton
import GenericField
import PasswordField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.gstv.primeirocompose.R


@Preview
@Composable
fun MainCreateUserContent() {
    FormNewUser()
}

@Composable
fun FormNewUser() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
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
        val passwordAreIdenticals = remember {
            mutableStateOf(true)
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
            GenericField(textState = nameState, emptySpace = true)
            EmailField(emailState = emailState)
            PasswordField(passwordState = passwordState, areIdenticals = remember {
                mutableStateOf(true)
            })
            PasswordField(
                passwordState = confirmPasswordState,
                hint = "Confirme sua senha",
                areIdenticals = passwordAreIdenticals
            )
            GenericButton({
                checkIfIsOkay()
            }, "Cadastrar")

        }

    }
}

private fun checkIfIsOkay() {

}
