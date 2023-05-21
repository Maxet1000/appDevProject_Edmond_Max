package com.example.tracsit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tracsit.databinding.FragmentHomelocationBinding

class HomeLocationFragment : Fragment(R.layout.fragment_homelocation) {

    private lateinit var binding: FragmentHomelocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomelocationBinding.inflate(layoutInflater)
        return binding.root
    }
}