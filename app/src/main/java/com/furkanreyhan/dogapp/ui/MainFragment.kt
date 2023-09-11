package com.furkanreyhan.dogapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanreyhan.dogapp.R
import com.furkanreyhan.dogapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHttpDog.setOnClickListener {
            val httpDogFragment = HttpDogFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, httpDogFragment)
            transaction.addToBackStack(null) // Geri düğmesine basıldığında fragmentı
            // geri almak için stack'e atıyor
            transaction.commit()

        }

        binding.btnRandomDog.setOnClickListener {
            val randomDogFragment = RandomDogFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, randomDogFragment )
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }


}