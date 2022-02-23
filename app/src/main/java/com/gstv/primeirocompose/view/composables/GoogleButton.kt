package com.gstv.primeirocompose.view.composables

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gstv.primeirocompose.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestEmail()
    .build()

@Composable
fun ButtonGoogle(content: ActivityResultLauncher<Intent>, signInIntent: Intent) {
     val isConnecting = remember {
        mutableStateOf(false)
    }
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(
                id = R.color.background
            )
        ),
        enabled = !isConnecting.value,
        modifier = Modifier.padding(start = 40.dp, end = 40.dp),
        onClick = {
            isConnecting.value = !isConnecting.value
            content.launch(signInIntent)
        }

    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.padding(end = 10.dp),
                painter = painterResource(id = R.drawable.ic_goggle),
                contentDescription = "Google Button",
                tint = Color.Unspecified
            )
            if (!isConnecting.value) {
                Text(text = "Sign Up with Google")
            } else {
                Text(text = "Connecting...", modifier = Modifier.padding(end = 15.dp))
                CircularProgressIndicator(
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 3.dp
                )
            }
        }
    }
}

