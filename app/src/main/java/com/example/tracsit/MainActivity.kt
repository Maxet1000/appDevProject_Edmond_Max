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

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var menuBarToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(menuBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}