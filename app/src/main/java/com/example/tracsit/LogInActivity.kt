package com.example.tracsit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tracsit.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar


class LogInActivity : AppCompatActivity() {
    public val passW = "pw"
    private lateinit var binding: ActivityLoginBinding
    private val RC_SIGN_IN = 9001
    private val TAG = "LogInActivity"
    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.logInButton.setOnClickListener { view ->
            Snackbar.make(view, snackBarOutPutTest(passW), Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
        setContentView(binding.root)

        binding.buttonGoogleLogIn.setOnClickListener{ goToMainScreen()}

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById<View>(R.id.sign_in_button).setOnClickListener{
            signIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            System.out.println(task)
            System.out.println("LOL1.......")
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            System.out.println(account)
            updateUI(account)
            System.out.println("LOL2.......")
        } catch (e: ApiException) {

            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            goToMainScreen()//TODO: toevoegen get data van account naar mainActivity
        }
    }
    override fun onStart(){
        super.onStart()
        var account = GoogleSignIn.getLastSignedInAccount(this)
        System.out.println(account)
        updateUI(account)
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

    fun goToMainScreen(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);
    }
}
