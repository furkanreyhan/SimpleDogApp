package com.furkanreyhan.dogapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.furkanreyhan.dogapp.databinding.FragmentHttpdogBinding
import com.bumptech.glide.Glide
import com.furkanreyhan.dogapp.R

class HttpDogFragment : Fragment() {

    private lateinit var binding: FragmentHttpdogBinding
    private lateinit var viewModel: HttpDogViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHttpdogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HttpDogViewModel::class.java)

        val dogs = resources.getStringArray(R.array.Dogs)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dogs)

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedDogUrl = dogs[position]
                viewModel.setSelectedDogImageUrl(selectedDogUrl)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        viewModel.selectedDogImageUrl.observe(viewLifecycleOwner, Observer { imageUrl ->
            imageUrl?.let {
                Glide.with(requireContext())
                    .load(it)
                    .into(binding.imageViewDog)
            }
        })
    }
}
