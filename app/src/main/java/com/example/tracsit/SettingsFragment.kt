package com.example.tracsit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tracsit.databinding.FragmentSettingsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment(R.layout.fragment_settings) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSettingsBinding
    private var homeLocationFragment = HomeLocationFragment()
    private var workLocationFragment = WorkLocationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

        binding.activeAtMonday.setOnCheckedChangeListener { ActivateMonday, isChecked ->
            travelInfo?.activeDays?.set(0, isChecked)
        }
        binding.activeAtTuesday.setOnCheckedChangeListener { ActiveAtTuesday, isChecked ->
            travelInfo?.activeDays?.set(1, isChecked)
        }
        binding.activeAtWednesday.setOnCheckedChangeListener { ActiveAtWednesday, isChecked ->
            travelInfo?.activeDays?.set(2, isChecked)
        }
        binding.activeAtThursday.setOnCheckedChangeListener { ActiveAtThursday, isChecked ->
            travelInfo?.activeDays?.set(3, isChecked)
        }
        binding.activeAtFriday.setOnCheckedChangeListener { ActiveAtFriday, isChecked ->
            travelInfo?.activeDays?.set(4, isChecked)
        }
        binding.activeAtSaturday.setOnCheckedChangeListener { ActiveAtSaturday, isChecked ->
            travelInfo?.activeDays?.set(5, isChecked)
        }
        binding.activeAtSunday.setOnCheckedChangeListener { ActiveAtSunday, isChecked ->
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}