package com.example.mydiary.presentation.features.entrance


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.lifecycleScope
import com.example.mydiary.databinding.EntranceBinding
import com.example.mydiary.di.App
import com.example.mydiary.presentation.features.common.MainActivity
import com.example.mydiary.presentation.features.common.di.SignInIpCredentialRequest
import com.example.mydiary.presentation.features.common.di.SignUpIpCredentialRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

class EntranceActivity : AppCompatActivity() {
    private lateinit var binding: EntranceBinding
    private var goingToSettings = false

    private val settingsLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.getBiometric(this)
        }

    @SignInIpCredentialRequest
    @Inject
    lateinit var credentialRequest: GetCredentialRequest

    @SignUpIpCredentialRequest
    @Inject
    lateinit var signUpRequest: GetCredentialRequest

    @Inject
    lateinit var viewModel: EntranceViewModel

    @SuppressLint("NewApi", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        (application as App).appComponent.entranceActivityComponent().create().inject(this)
        val dialog = NoWifiFragmentDialog()


        viewModel.start()
        lifecycleScope.launch {
            launch {
                viewModel.checkWithFingerprint.collect {
                    if (it != null)
                        if (it) {
                            Log.d(TAG, it.toString())
                            viewModel.getBiometric(this@EntranceActivity)
                        } else {
                            val intent = Intent(this@EntranceActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    else
                        binding.button.isClickable = true
                }
            }
            launch {
                viewModel.canGo.collect {
                    if (it == true) {
                        val intent = Intent(this@EntranceActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            launch {
                viewModel.biometricsError.collect {
                    if (it == BiometricPrompt.ERROR_NO_BIOMETRICS || it == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        settingsLauncher.launch(
                            Intent(Settings.ACTION_SETTINGS)
                        )
                    } else {
                        viewModel.getBiometric(this@EntranceActivity)
                    }
                }
            }
        }

        binding.button.setOnClickListener {
            if (!viewModel.checkForInternet(this))
                dialog.show(supportFragmentManager, "dialog")
            else {
                val credentialManager = CredentialManager.create(this@EntranceActivity)
                viewModel.getLoginUser(
                    this@EntranceActivity, credentialRequest, signUpRequest, credentialManager
                )

            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (goingToSettings) {
            goingToSettings = false
            return
        }
        viewModel.getBiometric(this)
    }


    companion object {
        const val TAG = "EntranceActivity"
    }
}

