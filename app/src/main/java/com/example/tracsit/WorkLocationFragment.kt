package com.example.tracsit

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.tracsit.databinding.FragmentHomelocationBinding
import com.example.tracsit.databinding.FragmentWorklocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import java.lang.Exception
import java.util.Locale

class WorkLocationFragment : Fragment(R.layout.fragment_worklocation) {

    private lateinit var binding: FragmentWorklocationBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    private val cancellationTokenSource = CancellationTokenSource()
    private lateinit var geocoder: Geocoder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        geocoder = Geocoder(requireContext(), Locale.getDefault())
    }

    private fun getLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )

            return
        }
        fusedLocationProviderClient.getCurrentLocation(priority, cancellationTokenSource.token)
            .addOnSuccessListener { location ->
                    Log.d("Location", "location is found: $location")
                    val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    binding.editStreetNameWork.setText(address?.get(0)?.thoroughfare)
                    binding.editPostalCodeWork.setText(address?.get(0)?.postalCode)
                    binding.editCityNameWork.setText(address?.get(0)?.locality)
                    binding.editCountryNameWork.setText(address?.get(0)?.countryName)
                    binding.editBuildingNumberWork.setText(address?.get(0)?.featureName)
                }

            .addOnFailureListener { exception -> //mag opzich weg
                Log.d("Location", "Oops location failed with exception: $exception")
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorklocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.getCurrentLocationWork.setOnClickListener{

            getLocation()
        }
    }
}