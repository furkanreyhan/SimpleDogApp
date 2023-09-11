package com.furkanreyhan.dogapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.furkanreyhan.dogapp.databinding.FragmentRandomdogBinding

class RandomDogFragment : Fragment() {

    private lateinit var binding: FragmentRandomdogBinding
    private lateinit var viewModel: RandomDogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomdogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RandomDogViewModel::class.java)

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                showLoadingAnimation()
            } else {
                hideLoadingAnimation()
            }
        })

        viewModel.dogImageUrl.observe(viewLifecycleOwner, Observer { imageUrl ->
            imageUrl?.let {
                binding.imageViewApiDog.load(it)
            }
        })

        viewModel.getRandomDogImage()

        binding.randomButton.setOnClickListener {
            viewModel.getRandomDogImage()
        }
    }

    private fun showLoadingAnimation() {
        binding.animationView.visibility = View.VISIBLE
        binding.animationView.playAnimation()
    }

    private fun hideLoadingAnimation() {
        binding.animationView.visibility = View.GONE
        binding.animationView.cancelAnimation()
    }
}