package com.example.tracsit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tracsit.databinding.FragmentChangelocationBinding

class ChangeLocationFragment : Fragment(R.layout.fragment_changelocation) {

    private lateinit var binding: FragmentChangelocationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChangelocationBinding.inflate(layoutInflater)
        return binding.root
    }
}