package com.mek.internshipproject.ui.fragments

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



        binding.textViewDescription.text = getLocation.description
        Glide.with(requireView()).load(getLocation.image).into(binding.imageViewCitiesPhoto)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}