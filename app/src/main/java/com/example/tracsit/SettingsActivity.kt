package com.example.tracsit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.tracsit.databinding.ActivitySettingsBinding

class SettingsActivity : Activity() {

    private lateinit var binding: ActivitySettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SetHomeLocation.setOnClickListener{ goToSetHomeLocationScreen() }
        binding.SetWorkLocation.setOnClickListener{ goToSetWorkLocationScreen() }

    }
    fun goToSetWorkLocationScreen(){
        val Intent = Intent(this, WorkLocationActivity::class.java)
        startActivity(Intent)
    }
    fun goToSetHomeLocationScreen(){
        val Intent = Intent(this, HomeLocationActivity::class.java)
        startActivity(Intent)
    }
}