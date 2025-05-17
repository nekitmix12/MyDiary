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
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.lifecycleScope
import com.example.mydiary.databinding.EntranceBinding
import com.example.mydiary.di.App
import com.example.mydiary.presentation.MainActivity
import com.example.mydiary.presentation.di.SignInIpCredentialRequest
import com.example.mydiary.presentation.di.SignUpIpCredentialRequest
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

class EntranceActivity : AppCompatActivity() {
    private lateinit var binding: EntranceBinding
    private var goingToSettings = false

    /* private val addAccountLauncher = registerForActivityResult(
         ActivityResultContracts.StartActivityForResult()
     ) { result: ActivityResult ->
         if (result.resultCode == Activity.RESULT_OK) {
             Log.d(TAG,"ok: ${result.data}")

         } else {

         }
     }
 */
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

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @SuppressLint("NewApi", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        (application as App).appComponent.entranceActivityComponent().create().inject(this)
        val dialog = NoWifiFragmentDialog()

        /*if (!viewModel.checkForInternet(this))
            dialog.show(supportFragmentManager, "dialog")*/
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
                    Log.d(TAG, it.toString() + " null")

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

        /*
                val credentialManager = CredentialManager.create(this@EntranceActivity)
                val isConnect = entranceViewModel.checkForInternet(this)*/

        binding.button.setOnClickListener {
            if (!viewModel.checkForInternet(this))
                dialog.show(supportFragmentManager, "dialog")
            else {

            }

            /* if (isConnect)
                entranceViewModel.getLoginUser(
                    this@EntranceActivity,
                    credentialRequest,
                    signUpRequest,
                    credentialManager
                )
            else {
                Toast.makeText(this, this.getString(R.string.there_is_not_wifi), Toast.LENGTH_SHORT)
                    .show()
                biometricPrompt.authenticate(promptInfo)*/

            // }
        }
    }

    override fun onResume() {
        super.onResume()
        if (goingToSettings) {
            // пользователь только что вернулся из настроек — сбросим флаг
            goingToSettings = false
            return
        }
        // иначе — нормально пере‑запустить BiometricPrompt
        viewModel.getBiometric(this)
    }


    companion object {
        const val TAG = "com.example.mydiary.presentation.EntranceActivity"
    }
}

