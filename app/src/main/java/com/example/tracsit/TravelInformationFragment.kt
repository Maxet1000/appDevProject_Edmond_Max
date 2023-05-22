package com.example.tracsit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tracsit.databinding.FragmentTravelInformationBinding

class TravelInformationFragment : Fragment(R.layout.fragment_travel_information) {

    private lateinit var binding: FragmentTravelInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d(travelInfo, "sss")
        //binding.fromLocationInfo.text = travelInfo
        //if (travelInfo != null) {
            //binding.fromLocationInfo.text = travelInfo.fromLocation
            //binding.toLocationInfo.text = travelInfo.toLocation
            //binding.travelTimeInfo.text = travelInfo.travelTime
        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTravelInformationBinding.inflate(layoutInflater)
        binding.fromLocationInfo.text = arguments?.getString("fromLocation")
        binding.toLocationInfo.text = arguments?.getString("toLocation")
        binding.travelTimeInfo.text = arguments?.getString("travelTime")
        return binding.root
    }
}