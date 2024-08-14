package com.example.mobiledevelopmenintern2024.Screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobiledevelopmenintern2024.R
import com.example.mobiledevelopmenintern2024.databinding.ActivitySecondScreenBinding

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var nameProp: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get all data from intent
        val name = intent.getStringExtra("name").toString()
        val username = intent.getStringExtra("username").toString()

        // Display the name and username
        if (name.isNotEmpty()) {
            binding.tvName.text = name
        } else {
            binding.tvName.text = "John Doe"
        }

        if (username.isNotEmpty()) {
            binding.tvUsername.visibility = View.INVISIBLE
            binding.tvSelected.visibility = View.VISIBLE
            binding.tvSelected.text = username
        } else {
            binding.tvUsername.visibility = View.VISIBLE
            binding.tvSelected.visibility = View.INVISIBLE
        }

        // Invoke button handlers
        btnBackHandler()
        btnChooseUser()

    }

    private fun btnBackHandler() {
        // Create intent to navigate to FirstScreen
        val intent = Intent(this, FirstScreen::class.java)

        // Move activity when btn_back is clicked
        binding.btnBack.setOnClickListener {
            startActivity(intent)
        }
    }
    private fun btnChooseUser() {
        // Move activity when btn_choose is clicked
        binding.btnChoose.setOnClickListener {
            // Create intent to navigate to ThirdScreen
            val intent = Intent(this, Thirdscreen::class.java).also {
                it.putExtra("name", binding.tvName.text.toString())
                startActivity(it)
            }
        }
    }
}
