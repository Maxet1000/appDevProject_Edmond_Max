package com.example.tracsit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tracsit.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar


class LogInActivity : AppCompatActivity() {
    public val passW = "pw"
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.logInButton.setOnClickListener { view ->
            Snackbar.make(view, snackBarOutPutTest(passW), Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
        setContentView(binding.root)

        binding.regIsterButton.setOnClickListener {  goToSettingsScreen()}
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
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent);
    }

    fun goToSettingsScreen(){
        val Intent = Intent(this, SettingsActivity::class.java)
        startActivity(Intent)

    }
}
