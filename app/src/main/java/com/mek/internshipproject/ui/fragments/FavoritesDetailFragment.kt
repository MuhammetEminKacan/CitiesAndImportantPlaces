package com.mek.internshipproject.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mek.internshipproject.R
import com.mek.internshipproject.databinding.FragmentFavoritesDetailBinding
import com.mek.internshipproject.ui.activities.MapsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesDetailFragment : Fragment() {
    private var _binding : FragmentFavoritesDetailBinding ?= null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle : FavoritesDetailFragmentArgs by navArgs()
        val getLocation = bundle.detail

        val lat = getLocation.coordinates?.lat ?: 0.0
        val lng = getLocation.coordinates?.lng ?: 0.0
        val name = getLocation.name ?: "bilinmeyen konum"
        binding.textViewDescription.text = getLocation.description
        Glide.with(requireView()).load(getLocation.image).into(binding.imageViewCitiesPhoto)

        binding.buttonGoToMaps.setOnClickListener {
            val intent = Intent(requireContext(),MapsActivity::class.java)
            intent.putExtra("lat",lat)
            intent.putExtra("lng",lng)
            intent.putExtra("name",name)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}