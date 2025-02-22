package com.example.mydiary.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.example.mydiary.databinding.EntranceBinding

class EntranceActivity : ComponentActivity() {
    private lateinit var binding: EntranceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        binding = EntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

