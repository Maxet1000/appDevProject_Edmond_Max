package com.example.tracsit

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.tracsit.databinding.FragmentTravelInformationBinding

class TravelInformationFragment : Fragment() {

    private lateinit var binding: FragmentTravelInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTravelInformationBinding.inflate(layoutInflater)
        var travelInfo = arguments?.getParcelable<TravelInformation>("TravelInfo")
        if (travelInfo != null) {
            binding.fromLocationInfo.text = "From: " + travelInfo.fromLocation?.getAddressLine(0)
            binding.toLocationInfo.text = "To: " + travelInfo.toLocation?.getAddressLine(0)
            binding.travelTimeInfo.text = "Travel Time: " + travelInfo.travelTime
            if( travelInfo.fromLocation == null){ binding.fromLocationInfo.text = "From: "}
            if( travelInfo.toLocation == null){ binding.toLocationInfo.text = "To: "}
            if( travelInfo.travelTime == null){ binding.travelTimeInfo.text = "TravelTime: "}
            binding.activeAtMondayInformation.isChecked = travelInfo.activeDays[0]
            binding.activeAtTuesdayInformation.isChecked = travelInfo.activeDays[1]
            binding.activeAtWednesdayInformation.isChecked = travelInfo.activeDays[2]
            binding.activeAtThursdayInformation.isChecked = travelInfo.activeDays[3]
            binding.activeAtFridayInformation.isChecked = travelInfo.activeDays[4]
            binding.activeAtSaturdayInformation.isChecked = travelInfo.activeDays[5]
            binding.activeAtSundayInformation.isChecked = travelInfo.activeDays[6]
        }
        return binding.root
    }
}