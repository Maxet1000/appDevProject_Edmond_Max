package com.example.tracsit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tracsit.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    public val passW = "pw"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.logInButton.setOnClickListener { view ->
            Snackbar.make(view, snackBarOutPutTest(passW), Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
        setContentView(binding.root)

        binding.regIsterButton.setOnClickListener {  goToWelcomeScreen()}

        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }


    //tijdelijke functie die ingegeven password tegen gehardcode password checked
    public fun correctPassword(passW: String ): Boolean {
        return passW.equals(binding.passWordLogIn.text.toString())

    }

    //tijdelijke functie vr snackbar
    fun snackBarOutPutTest(passWord: String): String {
        if (correctPassword(passWord)) {
            return "logged in"
        } else return "wrong password"
    }

    fun goToWelcomeScreen(){
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent);
    }

//Hierin komt de logica van het inloggen met google

}
