package com.example.mobiledevelopmenintern2024.Screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mobiledevelopmenintern2024.databinding.ActivityFirstScreenBinding

class FirstScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnNextHandler()
        btnCheckPalindrome()

    }

    private fun btnCheckPalindrome() {
        binding.btnCheck.setOnClickListener {
            // Get the input text for palindrome check
            val word = binding.inputPalindrome.text.toString()

            // Split the word into an array of characters
            val lettersOri: Array<String> = word.split("").toTypedArray()

            // Reverse the array of characters
            val lettersReverse: Array<String> = lettersOri.reversedArray()

            // Convert the reversed array back to a word
            val reversedWord = concatReversedLetter(lettersReverse)

            // Check if the reversed word matches the original word
            if (reversedWord == word) {
                Toast.makeText(this, "It's Palindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Palindrome", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*
    * private fun btnNextHandler
    * -> button next in first screen listener, to move to second screen
    *    with transfer data name which inputted in first screen
    * */
    private fun btnNextHandler() {
        binding.btnNext.setOnClickListener {
            // Check if the input name is empty
            if (binding.inputName.text.isEmpty()) {
                Toast.makeText(this, "Fill the name", Toast.LENGTH_SHORT).show()
            } else {
                // Create intent to move to SecondScreen
                val intent = Intent(this@FirstScreen, SecondScreen::class.java).also {
                    // Get the name from input and pass it to the next activity
                    val name = binding.inputName.text.toString()
                    if (name != "") {
                        it.putExtra("name", name)
                    } else {
                        it.putExtra("name", "John Doe")
                    }
                    it.putExtra("username", "")
                    startActivity(it)
                }
            }
        }
    }

    /*
     * private fun concatReversedLetter
     * -> is to concat reversed letter
     * */
    private fun concatReversedLetter(arr: Array<String>): String {
        var reversedWord = ""
        for (letter in arr) {
            reversedWord += letter
        }
        return reversedWord
    }
}