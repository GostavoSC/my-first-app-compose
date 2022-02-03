package com.gstv.primeirocompose.view

import ButtonLogin
import EmailField
import PasswordField
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import java.lang.RuntimeException

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gstv.primeirocompose.R
import com.gstv.primeirocompose.view.composables.*
import com.gstv.primeirocompose.viewModel.LoginActivityViewModel


class LoginActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val viewModel = LoginActivityViewModel()
    private val content = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            updateUi(account)
        } catch (e: RuntimeException) {
            //TO-DO treatment
        }

    }

    override fun onStart() {
        super.onStart()
        Firebase.auth.currentUser?.let {
            updateUi()
        }
        GoogleSignIn.getLastSignedInAccount(this)?.let {
            updateUi(it)
        }

    }

    private fun updateUi(account: GoogleSignInAccount? = null) {
        startActivity(
            Intent(this, HomeActivity::class.java).putExtra(
                "account",
                account
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
        setupLoginGoogle()
    }

    private fun setupLoginGoogle() {
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    @Preview
    @Composable
    private fun MainContent() {
        LoginAppBar()
        FormLogin()
        setupLogin()
    }

    @Composable
    fun FormLogin() {
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
                PasswordField(passwordState = passwordState)
                ButtonLogin {
                    viewModel.sendLogin(
                        email = emailState.value,
                        password = passwordState.value
                    )
                }
                CustomDivider()
                ButtonGoogle(content, mGoogleSignInClient.signInIntent)
            }
        }
    }

    private fun setupLogin() {
        viewModel.sendLoginLiveData.observe(this) {
            updateUi()
            finish()
        }
    }
}

