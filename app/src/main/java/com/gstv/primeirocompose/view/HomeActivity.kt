package com.gstv.primeirocompose.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.gstv.primeirocompose.view.composables.MainHomeContent

class HomeActivity : AppCompatActivity() {
    private var account: GoogleSignInAccount? = null

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUserAccount()
        setContent {

        }
    }

    private fun setupUserAccount() {
        if (intent.hasExtra("account")) {
            intent.extras?.get("account")?.let {
                account = (it as GoogleSignInAccount)
            }
        }
    }



}