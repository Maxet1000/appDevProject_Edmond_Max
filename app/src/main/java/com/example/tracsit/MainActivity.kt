package com.example.tracsit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.tracsit.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var menuBarToggle: ActionBarDrawerToggle
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToFragment(HomeFragment())
        setupMenuDrawer()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> goToFragment(HomeFragment())
                R.id.notifications -> goToFragment(NotificationsFragment())
                R.id.settings -> goToFragment(SettingsFragment())

                else -> {}
            }
            true
        }

    }

    private fun setupMenuDrawer() {
        menuBarToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.menu_open, R.string.menu_close)
        binding.drawerLayout.addDrawerListener(menuBarToggle)
        // it's now ready to be used
        menuBarToggle.syncState()

        // when the menu drawer opens, the toggle button moves to a "back" button and it will close again.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // handle menu drawer item clicks.
        // since these are all events that influence the fragment list, delegate their actions!
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                    R.id.logoutButton -> goToLoginScreen()
            }
            true
        }
    }

    private fun goToFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment_container_layout, fragment)
            addToBackStack("Fragment_${fragment.id}")
            commit()
        }
    }

    private fun goToLoginScreen(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        var googleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignInClient.signOut().addOnCompleteListener {
            val intent= Intent(this, LogInActivity::class.java)
            var auth = FirebaseAuth.getInstance()
            auth.signOut()
            startActivity(intent);
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(menuBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}