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
import com.bumptech.glide.Glide

class HttpDogFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_httpdog, container, false)

        //strings.xml de tanımladığımız Dogs arrayini dogs'a atıyoruz.
        val dogs = resources.getStringArray(R.array.Dogs)

        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val imageView = view.findViewById<ImageView>(R.id.ivDog)

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
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        return view
    }
}
