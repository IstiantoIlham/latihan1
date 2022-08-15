package com.istianto.first_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.istianto.first_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSnackBar.setOnClickListener {
            val snackBar =
                Snackbar.make(binding.root, "Ini adalah SnackBar", Snackbar.LENGTH_INDEFINITE)

            snackBar.setAction("Next") {
                Snackbar.make(binding.root, "Selamat Datang", Snackbar.LENGTH_SHORT).show()
            }.show()
        }

        binding.btnToast.setOnClickListener {
            Toast.makeText(this, "Ini adalah Toast", Toast.LENGTH_SHORT).show()
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}