package com.example.mydiary.presentation.entrance_activity


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.example.mydiary.R
import com.example.mydiary.databinding.EntranceBinding
import com.example.mydiary.di.App
import com.example.mydiary.presentation.di.SignInIpCredentialRequest
import com.example.mydiary.presentation.di.SignUpIpCredentialRequest
import java.util.concurrent.Executor
import javax.inject.Inject

class EntranceActivity : AppCompatActivity() {
    private lateinit var binding: EntranceBinding

    @SignInIpCredentialRequest
    @Inject
    lateinit var credentialRequest: GetCredentialRequest

    @SignUpIpCredentialRequest
    @Inject
    lateinit var signUpRequest: GetCredentialRequest

    @Inject
    lateinit var entranceViewModel: EntranceViewModel

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        (application as App).appComponent.entranceActivityComponent().create().inject(this)

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()


        val credentialManager = CredentialManager.create(this@EntranceActivity)
        val isConnect = checkForInternet(this)
        binding.button.setOnClickListener {
            if (isConnect)
                entranceViewModel.getLoginUser(
                    this@EntranceActivity,
                    credentialRequest,
                    signUpRequest,
                    credentialManager
                )
            else {
                Toast.makeText(this, this.getString(R.string.there_is_not_wifi), Toast.LENGTH_SHORT)
                    .show()
                biometricPrompt.authenticate(promptInfo)

            }/*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()*/
        }
    }

    @SuppressLint("ObsoleteSdkInt", "MissingPermission")
    private fun checkForInternet(context: Context): Boolean {

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

    companion object {
        const val TAG = "com.example.mydiary.presentation.EntranceActivity"
    }
}

