package com.example.tracsit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tracsit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToFragment(HomeFragment())

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

    private fun goToFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment_container_layout, fragment)
            addToBackStack("Fragment_${fragment.id}")
            commit()
        }
    }

}