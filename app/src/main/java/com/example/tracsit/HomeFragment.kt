package com.example.tracsit

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracsit.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var geocoder: Geocoder

    private var infoAdapter: InformationAdapter = InformationAdapter()

    lateinit var model: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        geocoder = Geocoder(requireContext(), Locale.getDefault())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val view = binding.root
        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = infoAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        infoAdapter.setOnItemClickListener(object: InformationAdapter.onItemClickListener{
            override fun onItemClicked(position: Int) {
                val bundle = Bundle()
                val f = TravelInformationFragment()
                model.sendMessage(infoAdapter.getTravelInformation()[position])
                bundle.putParcelable("TravelInfo", infoAdapter.getTravelInformation()[position])
                f.arguments = bundle
                goToFragment(f)
            }
        })

        binding.addTravelRout.setOnClickListener{
            infoAdapter.addTravelRoute()
            infoAdapter.notifyItemInserted(infoAdapter.itemCount + 1)
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