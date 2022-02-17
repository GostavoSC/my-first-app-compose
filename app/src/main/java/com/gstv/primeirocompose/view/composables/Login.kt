import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.gstv.primeirocompose.R
import com.gstv.primeirocompose.view.composables.ButtonGoogle
import com.gstv.primeirocompose.view.composables.CustomDivider
import com.gstv.primeirocompose.view.composables.GenericAppBar
import com.gstv.primeirocompose.viewModel.LoginActivityViewModel


@Composable
fun MainContent(
    content: ActivityResultLauncher<Intent>,
    viewModel: LoginActivityViewModel,
    mGoogleSignInClient: GoogleSignInClient,
    navController: NavHostController
) {
    GenericAppBar()
    FormLogin(content, viewModel, mGoogleSignInClient, navController)
}

@Composable
fun FormLogin(
    content: ActivityResultLauncher<Intent>,
    viewModel: LoginActivityViewModel,
    mGoogleSignInClient: GoogleSignInClient,
    navController: NavHostController
) {
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()

    ) {

        val column = createRef()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .padding(10.dp)

        ) {
            EmailField(emailState = emailState)
            PasswordField(passwordState = passwordState, areIdenticals = remember {
                mutableStateOf(true)
            })
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { navController.navigate("cadastro") },
                    modifier = Modifier.padding(top = 25.dp, end = 10.dp)
                ) {
                    Text(
                        text = "Sou novo",
                        color = colorResource(id = R.color.blue_base),
                        fontWeight = FontWeight.Bold
                    )
                }

                GenericButton(text = "Login", onClick = {
                    viewModel.sendLogin(
                        email = emailState.value,
                        password = passwordState.value,

                        )
                })
            }
            CustomDivider()
            ButtonGoogle(content, mGoogleSignInClient.signInIntent)
        }
    }
}


@Composable
fun GenericButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.padding(top = 25.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(
                id = R.color.blue_base
            )
        )
    ) {
        Text(
            color = colorResource(id = R.color.white),
            text = text
        )
    }
}

@Composable
fun EmailField(emailState: MutableState<String>) {

    val black = colorResource(
        id = R.color.blue_base
    )
    val isValidEmail = remember {
        mutableStateOf(true)
    }

    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = black,
            cursorColor = black,
            focusedLabelColor = black,
            focusedBorderColor = black
        ),
        isError = !isValidEmail.value,
        singleLine = true,
        value = emailState.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        onValueChange = {
            emailState.value = it
            isValidEmail.value = !it.contains(" ")

        },
        label = { Text(text = stringResource(id = R.string.email_hint)) }
    )
}

@Composable
fun GenericField(textState: MutableState<String>, emptySpace: Boolean) {
    val black = colorResource(
        id = R.color.blue_base
    )
    val isValidEmail = remember {
        mutableStateOf(true)
    }

    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = black,
            cursorColor = black,
            focusedLabelColor = black,
            focusedBorderColor = black
        ),
        isError = !isValidEmail.value,
        singleLine = true,
        value = textState.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        onValueChange = {
            textState.value = it
            if (!emptySpace) {
                isValidEmail.value = !it.contains(" ")
            }

        },
        label = { Text(text = "Digite seu nome completo") }
    )
}


@Composable
fun PasswordField(
    passwordState: MutableState<String>,
    hint: String = stringResource(id = R.string.password),
    areIdenticals: MutableState<Boolean>
) {

    val black = colorResource(
        id = R.color.blue_base
    )

    val isPasswordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = black,
            cursorColor = black,
            focusedLabelColor = black,
            focusedBorderColor = black
        ),
        isError = !areIdenticals.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = {
                isPasswordVisible.value = !isPasswordVisible.value
            }) {
                if (!isPasswordVisible.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_https_24),
                        contentDescription = null
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_lock_open_24),
                        contentDescription = null
                    )
                }

            }
        },
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = { Text(text = hint) }
    )
}