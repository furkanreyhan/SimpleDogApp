package com.furkanreyhan.dogapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.airbnb.lottie.LottieAnimationView
import com.furkanreyhan.dogapp.databinding.FragmentRandomdogBinding
import com.furkanreyhan.dogapp.model.DogResponse
import com.furkanreyhan.dogapp.network.RemoteService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomDogFragment : Fragment() {

    private lateinit var binding: FragmentRandomdogBinding
    private lateinit var animationView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomdogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationView = binding.ltAnimation
        showLoadingAnimation()

        val dogApi = RemoteService.dogApi
        val call = dogApi.getDog()

        call.enqueue(object : Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                if (response.isSuccessful) {
                    val dogResponse = response.body()
                    val imageUrl = dogResponse?.message

                    imageUrl?.let {
                        binding.ivApiDog.load(it)
                        hideLoadingAnimation()
                    }
                } else {
                    hideLoadingAnimation()
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
            }
        })
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