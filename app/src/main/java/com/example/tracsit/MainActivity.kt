package com.example.tracsit
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.tracsit.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)

    }
}