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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tracsit.databinding.FragmentHomelocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.Locale

class HomeLocationFragment : Fragment(R.layout.fragment_homelocation) {

    private lateinit var binding: FragmentHomelocationBinding

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var geocoder: Geocoder
    private val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    private val cancellationTokenSource = CancellationTokenSource()

    private var travelInfo: TravelInformation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        geocoder = Geocoder(requireContext(), Locale.getDefault())
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomelocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getCurrentLocationHome.setOnClickListener{
            getLocation()
        }

        binding.confirmHomeLocationButton.setOnClickListener {
            saveLocation()
            parentFragmentManager.popBackStack()
        }

        val model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        travelInfo = model.message.value
        model.message.observe(viewLifecycleOwner, Observer {
            binding.editStreetNameHome.setText(it.fromLocation?.thoroughfare)
            binding.editPostalCodeHome.setText(it.fromLocation?.postalCode)
            binding.editCityNameHome.setText(it.fromLocation?.locality)
            binding.editCountryNameHome.setText(it.fromLocation?.countryName)
            binding.editBuildingNumberHome.setText(it.fromLocation?.featureName)
        })
    }

    private fun getLocation() {
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
                try { //doet niet veel aan het crashen van de geocoder "java.lang.RuntimeException: java.lang.reflect.InvocationTargetException". Werkt wel wanneer gps uit staat, zie ook WorkLocationFragment
                    val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    binding.editStreetNameHome.setText(address?.get(0)?.thoroughfare)
                    binding.editPostalCodeHome.setText(address?.get(0)?.postalCode)
                    binding.editCityNameHome.setText(address?.get(0)?.locality)
                    binding.editCountryNameHome.setText(address?.get(0)?.countryName)
                    binding.editBuildingNumberHome.setText(address?.get(0)?.featureName)
                } catch(e: Exception){
                    Toast.makeText(context, "No location found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception -> //mag opzich weg
                Log.d("Location", "Oops location failed with exception: $exception")
            }
    }

    private fun saveLocation() {
        if(binding.editCityNameHome.text.toString().trim().isNotEmpty() && binding.editCountryNameHome.text.toString().trim().isNotEmpty()) {
            val guessLocation =  "" + binding.editStreetNameHome.text +
                                " " + binding.editPostalCodeHome.text +
                                " " + binding.editCityNameHome.text +
                                " " + binding.editCountryNameHome.text +
                                      binding.editBuildingNumberHome.text

            try {
                val guessedLocation = geocoder.getFromLocationName(guessLocation, 5)
                if (guessedLocation == null) {
                    Toast.makeText(requireContext(), "No location found", Toast.LENGTH_SHORT).show()
                } else {
                    travelInfo?.fromLocation = guessedLocation[0]
                    Toast.makeText(requireContext(), "Location saved successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (ex: java.lang.Exception) {
                Toast.makeText(context, "No location found", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Invalid Input, at least City and Country are required", Toast.LENGTH_SHORT).show()
        }
    }
}