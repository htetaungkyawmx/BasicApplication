package org.hak.basicapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnSubmit: Button
    private lateinit var btnEntry: Button
    private lateinit var btnChoose: Button
    private lateinit var spinnerCity: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        initViews()

        // Setup spinner
        setupSpinner()

        // Setup click listeners
        setupClickListeners()

        // Modern way to handle back button
        setupBackPressedHandler()
    }

    private fun initViews() {
        btnChoose = findViewById(R.id.btnChoose)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnEntry = findViewById(R.id.btnEntry)
        spinnerCity = findViewById(R.id.spinnerCity)
    }

    private fun setupSpinner() {
        val cities = arrayOf("Select Your City", "Yangon", "Mandalay", "Bago", "Mawlamyine")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter = adapter
    }

    private fun setupClickListeners() {
        btnChoose.setOnClickListener {
            val selectedCity = spinnerCity.selectedItem.toString()
            if(selectedCity == "Select Your City"){
                Toast.makeText(this,"Please choose your city", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Your selected city is $selectedCity", Toast.LENGTH_LONG).show()
            }
        }

        btnSubmit.setOnClickListener {
            val selectedCity = spinnerCity.selectedItem.toString()
            if(selectedCity == "Select Your City"){
                Toast.makeText(this,"Please choose your city first", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "City $selectedCity submitted successfully!", Toast.LENGTH_LONG).show()
            }
        }

        btnEntry.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupBackPressedHandler() {
        // Modern way to handle back button (recommended)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToEntryActivity()
            }
        })
    }

    private fun navigateToEntryActivity() {
        val intent = Intent(this, EntryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    /*
    override fun onBackPressed() {
        navigateToEntryActivity()
    }
    */
}