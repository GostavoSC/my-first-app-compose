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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.firebase.ui.auth.util.ui.fieldvalidators.PasswordFieldValidator
import com.gstv.primeirocompose.R

@Composable
fun ButtonLogin(onClick: () -> Unit) {
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
            text = stringResource(
                id = R.string.login
            )

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
fun PasswordField(passwordState: MutableState<String>) {

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
        label = { Text(text = stringResource(id = R.string.password)) }
    )
}