package com.example.mydiary.presentation.features.entrance


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.example.mydiary.R
import com.example.mydiary.databinding.EntranceBinding
import com.example.mydiary.di.App
import com.example.mydiary.presentation.MainActivity
import com.example.mydiary.presentation.di.SignInIpCredentialRequest
import com.example.mydiary.presentation.di.SignUpIpCredentialRequest
import java.util.concurrent.Executor
import javax.inject.Inject

class EntranceActivity : AppCompatActivity() {
    private lateinit var binding: EntranceBinding

   /* private val addAccountLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d(TAG,"ok: ${result.data}")

        } else {

        }
    }
*/
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

    @SuppressLint("NewApi", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        (application as App).appComponent.entranceActivityComponent().create().inject(this)



/*
        val credentialManager = CredentialManager.create(this@EntranceActivity)
        val isConnect = entranceViewModel.checkForInternet(this)*/
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

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


    companion object {
        const val TAG = "com.example.mydiary.presentation.EntranceActivity"
    }
}

