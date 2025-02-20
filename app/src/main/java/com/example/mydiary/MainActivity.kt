package com.example.mydiary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.databinding.MainScreenBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}