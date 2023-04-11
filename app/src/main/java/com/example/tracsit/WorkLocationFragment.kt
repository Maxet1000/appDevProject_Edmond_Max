package com.example.tracsit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tracsit.databinding.FragmentWorklocationBinding

class WorkLocationFragment : Fragment(R.layout.fragment_worklocation) {

    private lateinit var binding: FragmentWorklocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorklocationBinding.inflate(layoutInflater)
        return binding.root
    }
}