package com.example.mobiledevelopmenintern2024

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobiledevelopmenintern2024.Screens.FirstScreen
import com.example.mobiledevelopmenintern2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToFirstScreen()
    }
    private fun navigateToFirstScreen() {
        val intent = Intent(this, FirstScreen::class.java)
        startActivity(intent)
    }
}