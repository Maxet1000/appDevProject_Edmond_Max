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
import com.example.tracsit.databinding.FragmentWorklocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.Locale
import kotlin.Exception

class WorkLocationFragment : Fragment(R.layout.fragment_worklocation) {

    private lateinit var binding: FragmentWorklocationBinding

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
        binding = FragmentWorklocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getCurrentLocationWork.setOnClickListener{
            getLocation()
        }

        binding.ConfirmWorkLocationButton.setOnClickListener {
            saveLocation()
            parentFragmentManager.popBackStack()
        }

        val model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        travelInfo = model.message.value
        model.message.observe(viewLifecycleOwner, Observer {
            binding.editStreetNameWork.setText(it.toLocation?.thoroughfare)
            binding.editPostalCodeWork.setText(it.toLocation?.postalCode)
            binding.editCityNameWork.setText(it.toLocation?.locality)
            binding.editCountryNameWork.setText(it.toLocation?.countryName)
            binding.editBuildingNumberWork.setText(it.toLocation?.featureName)
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
                try{ //zie commentaar HomeLocationFragment, geld hier ook
                    val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    binding.editStreetNameWork.setText(address?.get(0)?.thoroughfare)
                    binding.editPostalCodeWork.setText(address?.get(0)?.postalCode)
                    binding.editCityNameWork.setText(address?.get(0)?.locality)
                    binding.editCountryNameWork.setText(address?.get(0)?.countryName)
                    binding.editBuildingNumberWork.setText(address?.get(0)?.featureName)
                } catch(e: Exception){
                    Toast.makeText(context, "No location found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception -> //mag opzich weg
                Log.d("Location", "Oops location failed with exception: $exception")
            }
    }

    private fun saveLocation() {
        if(binding.editCityNameWork.text.toString().trim().isNotEmpty() && binding.editCountryNameWork.text.toString().trim().isNotEmpty()) {
            val guessLocation =  "" + binding.editStreetNameWork.text +
                                " " + binding.editPostalCodeWork.text +
                                " " + binding.editCityNameWork.text +
                                " " + binding.editCountryNameWork.text +
                                      binding.editBuildingNumberWork.text

            try {
                val guessedLocation = geocoder.getFromLocationName(guessLocation, 5)
                if (guessedLocation == null) {
                    Toast.makeText(requireContext(), "No location found", Toast.LENGTH_SHORT).show()
                } else {
                    travelInfo?.toLocation = guessedLocation[0]
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