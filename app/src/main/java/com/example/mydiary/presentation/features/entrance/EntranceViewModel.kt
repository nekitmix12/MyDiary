package com.example.mydiary.presentation.features.entrance

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydiary.R
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.usecase.GetSettingsUseCase
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EntranceViewModel @Inject constructor(private val getSettingsUseCase: GetSettingsUseCase) :
    ViewModel() {
    @StringRes
    private var _errorRef = MutableSharedFlow<Int>()
    val errorRef = _errorRef as SharedFlow<Int>

    @StringRes
    private var _entranceResult = MutableStateFlow<Int?>(null)
    val entranceResult: StateFlow<Int?> = _entranceResult

    private var isNeedBlock = true

    fun getSettings() {
        viewModelScope.launch {
            getSettingsUseCase.execute(GetSettingsUseCase.Request())
                .collect {
                    when (it) {
                        is Result.Success -> {
                            isNeedBlock = it.data.emotions.isUseFingerprint
                            if (!isNeedBlock)
                                _entranceResult.value = R.string.hello
                        }

                        is Result.Error -> {}
                        is Result.Loading -> {}
                    }
                }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    fun checkForInternet(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }

    }

    fun a(context: Context) {
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(context as FragmentActivity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        context, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()
    }

    fun getLoginUser(
        context: Context,
        credentialRequest: GetCredentialRequest,
        signUpRequest: GetCredentialRequest,
        credentialManager: CredentialManager
    ) {
        viewModelScope.launch {
            try {
                try {
                    val pendingResponse = credentialManager.getCredential(
                        context,
                        credentialRequest
                    )
                    handleSignIn(pendingResponse)
                } catch (e: NoCredentialException) {
                    Log.e(TAG, "No saved credentials", e)

                    _errorRef.emit(R.string.there_is_not_account)
                    try {
                        val signUpResponse = credentialManager.getCredential(
                            context,
                            signUpRequest
                        )
                        handleSignIn(signUpResponse)
                    } catch (e: Exception) {
                        Log.e(TAG, "Ошибка при регистрации", e)
                        val intent = Intent(Settings.ACTION_ADD_ACCOUNT).apply {
                            putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
                        }
                        context.startActivity(intent)
                    }
                } catch (e: GetCredentialException) {
                    Log.e(TAG, "CredentialManager failed", e)
                    _errorRef.emit(R.string.something_bad)

                } catch (e: Exception) {
                    Log.e(TAG, "Unexpected error", e)
                    _errorRef.emit(R.string.something_bad)
                }


            } catch (e: NoCredentialException) {
                _errorRef.emit(R.string.something_bad)
            }
        }
    }

    private fun handleSignIn(result: GetCredentialResponse): String? {
        val credential = result.credential
        Log.d(TAG, "Got credential of type ${credential::class.java.name}")
        when (credential) {
            is PublicKeyCredential -> {
                val responseJson = credential.authenticationResponseJson
                Log.d(TAG, responseJson)
            }

            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {

                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)


                        Log.e(TAG, googleIdTokenCredential.toString())
                        Log.e(TAG, credential.data.toString())
                        return "${googleIdTokenCredential.displayName} ${googleIdTokenCredential.familyName ?: ""}"
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                }

            }

            else -> {
                Log.e(TAG, "Unexpected type of credential")
            }
        }
        return null
    }

    companion object {
        const val TAG = "EntranceViewModel"
    }
}