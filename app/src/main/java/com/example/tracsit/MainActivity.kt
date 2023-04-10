package com.example.tracsit
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tracsit.databinding.ActivityLoginBinding
import com.example.tracsit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var main: ActivityMainBinding
    private lateinit var login: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(login.root)

    }
}