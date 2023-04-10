package com.example.tracsit
import android.app.Activity
import android.os.Bundle
import com.example.tracsit.databinding.ActivityLoginBinding


class LogInActivity : Activity()  {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

//Hierin komt de logica van het inloggen met google

}