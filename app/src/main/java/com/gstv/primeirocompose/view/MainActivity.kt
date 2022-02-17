package com.gstv.primeirocompose.view

import MainContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gstv.primeirocompose.view.composables.MainCreateUserContent
import com.gstv.primeirocompose.view.composables.MainHomeContent
import com.gstv.primeirocompose.view.composables.gso
import com.gstv.primeirocompose.viewModel.LoginActivityViewModel


class MainActivity : ComponentActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val viewModel = LoginActivityViewModel()
    private lateinit var gAccount: GoogleSignInAccount
    private lateinit var navController: NavHostController
    private val content = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            gAccount = task.getResult(ApiException::class.java)
            navigateToHome()
        } catch (e: RuntimeException) {
            //TO-DO treatment
        }

    }

    private fun navigateToHome() {
        navController.navigate("home")
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    MainContent(content, viewModel, mGoogleSignInClient, navController)
                }
                composable("home") {
                    MainHomeContent(gAccount)
                }
                composable("cadastro") {
                    MainCreateUserContent()
                }
            }
        }
        setupLoginGoogle()
        setupLogin()
    }

    private fun setupLoginGoogle() {
        GoogleSignIn.getLastSignedInAccount(this)?.let {
            gAccount = it
            navigateToHome()
        }
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun setupLogin() {
        viewModel.sendLoginLiveData.observe(this) {
            navigateToHome()
            finish()
        }
    }

}

