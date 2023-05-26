package com.example.tracsit

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracsit.databinding.FragmentHomeBinding
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentHomeBinding

    private lateinit var infoAdapter: InformationAdapter

    private lateinit var geocoder: Geocoder

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        geocoder = Geocoder(requireContext(), Locale.getDefault())
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var TestAddress = geocoder.getFromLocation(50.84037665757134, 4.362440673192904, 1)
        infoAdapter.getTravelInformation().add(TravelInformation(TestAddress?.get(0),TestAddress?.get(0), "00:10"))
        infoAdapter.setOnItemClickListener(object: InformationAdapter.onItemClickListener{
            override fun onItemClicked(position: Int) {
                Toast.makeText(activity, "Clicked on $position", Toast.LENGTH_SHORT).show()
                var bundle = Bundle()
                //bundle.putString("fromLocation", infoAdapter.getTravelInformation()[position].fromLocation)
                //bundle.putString("toLocation", infoAdapter.getTravelInformation()[position].toLocation)
                //bundle.putString("travelTime", infoAdapter.getTravelInformation()[position].travelTime)
                bundle.putParcelable("TravelInfo", infoAdapter.getTravelInformation()[position])
                var f = TravelInformationFragment()
                f.arguments = bundle
                goToFragment(f)
            }
        })
        binding.addTravelRout.setOnClickListener{
            infoAdapter.addTravelRoute()
            infoAdapter.notifyItemInserted(infoAdapter.itemCount + 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        infoAdapter = InformationAdapter()
        val view = binding.root
        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = infoAdapter
        }
        return view
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}