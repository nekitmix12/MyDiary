package com.example.mydiary.presentation.features.entrance

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.NoCredentialException
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.eventFlow
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.mydiary.R
import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.usecase.ChangeSettingsUseCase
import com.example.mydiary.domain.usecase.GetSettingsUseCase
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EntranceViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: ChangeSettingsUseCase
) : ViewModel() {
    private var settings = MutableStateFlow<SettingsModel?>(null)

    private var _checkWithFingerprint = MutableSharedFlow<Boolean?>()
    val checkWithFingerprint: SharedFlow<Boolean?> = _checkWithFingerprint

    private var _canGo = MutableStateFlow<Boolean?>(null)
    val canGo: StateFlow<Boolean?> = _canGo

    private var _biometricsError = MutableSharedFlow<Int>()
    val biometricsError: SharedFlow<Int> = _biometricsError

    fun start() {
        viewModelScope.launch {
            launch {
                getSettings()
            }
            launch {
                checkSettings()
            }
            launch {

            }
        }
    }

    private suspend fun checkSettings() {
        settings.collect {
            if (it != null && it.name.isNotBlank()) {
                _checkWithFingerprint.emit(it.isUseFingerprint)
            } else
                _checkWithFingerprint.emit(null)
            Log.d(TAG, "it: $it")
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
            @Suppress("DEPRECATION") return networkInfo.isConnected
        }

    }

    fun getBiometric(context: Context) {
        Log.d(TAG, (context as EntranceActivity).lifecycle.currentState.name)

        if (settings.value == null || settings.value!!.name == "") viewModelScope.launch {
            _checkWithFingerprint.emit(null)
        } else {
            viewModelScope.launch {
                (context).lifecycle.eventFlow.collect {
                    Log.d(TAG, "if have settings: " + it.name)

                }
            }

            val executor = ContextCompat.getMainExecutor(context)
            val biometricPrompt = BiometricPrompt(context as FragmentActivity,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(
                        errorCode: Int, errString: CharSequence
                    ) {
                        super.onAuthenticationError(errorCode, errString)
                        context.lifecycleScope.launch {
                            delay(100)
                            _biometricsError.emit(errorCode)
                        }

                    }

                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult
                    ) {
                        super.onAuthenticationSucceeded(result)
                        context.lifecycleScope.launch {
                            Log.d(TAG, "correct biometric")
                            _canGo.emit(true)
                        }
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        context.lifecycleScope.launch {
                            _biometricsError.emit(-1)
                        }
                    }
                })

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(context.getString(R.string.biometric_login))
                .setNegativeButtonText(context.getString(R.string.go_to_settings)).build()
            biometricPrompt.authenticate(promptInfo)
        }
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
                        context, credentialRequest
                    )
                    handleSignIn(pendingResponse)
                } catch (e: NoCredentialException) {
                    Log.e(TAG, "No saved credentials", e)
                    try {
                        val signUpResponse = credentialManager.getCredential(
                            context, signUpRequest
                        )
                        handleSignIn(signUpResponse)
                    } catch (e: Exception) {
                        Log.e(TAG, "Ошибка при регистрации", e)
                        val intent = Intent(Settings.ACTION_ADD_ACCOUNT).apply {
                            putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
                        }
                        context.startActivity(intent)
                    }

                } catch (e: Exception) {
                    Log.e(TAG, "Unexpected error", e)
                }


            } catch (_: NoCredentialException) {
            }
        }
    }

    private fun handleSignIn(result: GetCredentialResponse) {
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

                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)


                        Log.e(TAG, googleIdTokenCredential.toString())
                        Log.e(TAG, credential.data.toString())
                        viewModelScope.launch {
                            setSettingsUseCase.execute(
                                ChangeSettingsUseCase.Request(
                                    settings.value?.copy(
                                        name = googleIdTokenCredential.displayName ?: ""
                                    ) ?: SettingsModel(
                                        imageUrl = "",
                                        isSendRemindOn = false,
                                        isUseFingerprint = false,
                                        name = googleIdTokenCredential.displayName ?: ""
                                    )
                                )
                            ).collect {
                                when (it) {
                                    is Result.Error -> {}
                                    is Result.Success -> {
                                        Log.d(TAG, "get account")
                                        _canGo.emit(true)
                                    }

                                    is Result.Loading -> {}
                                }
                            }
                        }

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

    private suspend fun getSettings() {
        getSettingsUseCase.execute(GetSettingsUseCase.Request()).collect {
            when (it) {
                is Result.Success -> {
                    settings.emit(it.data.setting)
                }

                is Result.Error -> {}
                is Result.Loading -> {}
            }
        }
    }


    companion object {
        const val TAG = "EntranceViewModel"
    }
}