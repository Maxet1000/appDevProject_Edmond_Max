package com.example.tracsit
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tracsit.databinding.ActivityLoginBinding


class LogInActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

//Hierin komt de logica van het inloggen met google

}