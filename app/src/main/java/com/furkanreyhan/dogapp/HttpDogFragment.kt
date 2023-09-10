package com.furkanreyhan.dogapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.furkanreyhan.dogapp.databinding.FragmentHttpdogBinding
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide

class HttpDogFragment : Fragment() {

    private lateinit var binding : FragmentHttpdogBinding
    private lateinit var animationView: LottieAnimationView
    private lateinit var spinner: Spinner
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHttpdogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationView = binding.ltAnimation
        showLoadingAnimation()

        spinner = binding.spinner
        imageView = binding.ivDog

        //strings.xml de tanımladığımız Dogs arrayini dogs'a atıyoruz.
        val dogs = resources.getStringArray(R.array.Dogs)


        if (spinner != null && imageView != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dogs)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val selectedDogUrl = dogs[position]
                    Glide.with(requireContext())
                        .load(selectedDogUrl)
                        .into(imageView)
                    hideLoadingAnimation()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
    }

    private fun showLoadingAnimation() {
        animationView.visibility = View.VISIBLE
        animationView.playAnimation()
    }

    private fun hideLoadingAnimation() {
        animationView.visibility = View.GONE
        animationView.cancelAnimation()
    }
}
