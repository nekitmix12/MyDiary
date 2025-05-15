package com.example.mydiary.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.credentials.GetCredentialException
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import com.example.mydiary.R
import com.example.mydiary.databinding.EntranceBinding
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EntranceActivity : ComponentActivity() {
    private lateinit var binding: EntranceBinding

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        binding = EntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {

            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(true)
                .setServerClientId(getString(R.string.web_client_id))
                .setAutoSelectEnabled(true)
                .build()

            val request: androidx.credentials.GetCredentialRequest =
                androidx.credentials.GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

            val credentialManager = CredentialManager.create(this)

            CoroutineScope(Dispatchers.Default + Job()).launch {
                try {
                    val result = credentialManager.getCredential(
                        request = request,
                        context = this@EntranceActivity,
                    )
                    handleSignIn(result)
                } catch (@SuppressLint("NewApi") _: GetCredentialException) {
                }
            }
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()*/
        }
    }

    private fun handleSignIn(result: GetCredentialResponse) {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                }

            }

            else -> {
                Log.e(TAG, "Unexpected type of credential")
            }
        }
    }

    companion object {
        const val TAG = "EntranceActivity"
    }
}

