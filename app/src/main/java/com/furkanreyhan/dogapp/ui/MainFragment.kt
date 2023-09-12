package com.furkanreyhan.dogapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.furkanreyhan.dogapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHttpDog.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToHttpDogFragment()
            findNavController().navigate(action)
        }
        binding.btnRandomDog.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToRandomDogFragment()
            findNavController().navigate(action)
        }
    }


}