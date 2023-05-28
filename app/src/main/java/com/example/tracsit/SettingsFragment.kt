package com.example.tracsit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tracsit.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    private var homeLocationFragment = HomeLocationFragment()
    private var workLocationFragment = WorkLocationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val travelInfo = model.message.value
        model.message.observe(viewLifecycleOwner, Observer{
            binding.activeAtMonday.isChecked = it.activeDays[0]
            binding.activeAtTuesday.isChecked = it.activeDays[1]
            binding.activeAtWednesday.isChecked = it.activeDays[2]
            binding.activeAtThursday.isChecked = it.activeDays[3]
            binding.activeAtFriday.isChecked = it.activeDays[4]
            binding.activeAtSaturday.isChecked = it.activeDays[5]
            binding.activeAtSunday.isChecked = it.activeDays[6]
        })

        binding.setHomeLocation.setOnClickListener{
            goToFragment(homeLocationFragment)
        }
        binding.setWorkLocation.setOnClickListener{
            goToFragment(workLocationFragment)
        }

        binding.activeAtMonday.setOnCheckedChangeListener { _, isChecked ->
            travelInfo?.activeDays?.set(0, isChecked)
        }
        binding.activeAtTuesday.setOnCheckedChangeListener { _, isChecked ->
            travelInfo?.activeDays?.set(1, isChecked)
        }
        binding.activeAtWednesday.setOnCheckedChangeListener { _, isChecked ->
            travelInfo?.activeDays?.set(2, isChecked)
        }
        binding.activeAtThursday.setOnCheckedChangeListener { _, isChecked ->
            travelInfo?.activeDays?.set(3, isChecked)
        }
        binding.activeAtFriday.setOnCheckedChangeListener { _, isChecked ->
            travelInfo?.activeDays?.set(4, isChecked)
        }
        binding.activeAtSaturday.setOnCheckedChangeListener { _, isChecked ->
            travelInfo?.activeDays?.set(5, isChecked)
        }
        binding.activeAtSunday.setOnCheckedChangeListener { _, isChecked ->
            travelInfo?.activeDays?.set(6, isChecked)
        }
    }

    private fun goToFragment(fragment : Fragment){
        parentFragmentManager.beginTransaction().apply{
            replace(R.id.fragment_container_layout, fragment)
            addToBackStack("Fragment_${fragment.id}")
            commit()
        }
    }
}