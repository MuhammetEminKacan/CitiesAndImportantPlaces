package com.mek.internshipproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mek.internshipproject.databinding.FragmentFavoritesBinding
import com.mek.internshipproject.model.Location
import com.mek.internshipproject.ui.adapters.FavoritesAdapter
import com.mek.internshipproject.ui.viewModels.FavoritesViewModel
import com.mek.internshipproject.util.OnFavoriteClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private var _binding : FragmentFavoritesBinding ?= null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavoritesViewModel>()
    private lateinit var adapter : FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerViewAdapter()
    }

    private fun prepareRecyclerViewAdapter() {
        adapter = FavoritesAdapter(object : OnFavoriteClickListener{
            override fun onFavoriteClick(location: Location, isFavorite: Boolean) {
                if (!isFavorite){
                    viewModel.deleteLocation(location)
                }
            }

        })
        binding.recyclerViewFavoritesPlace.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavoritesPlace.adapter = adapter

        observeData()
    }

    private fun observeData() {
        viewModel.favoritesPlace.observe(viewLifecycleOwner){ locationList ->
            adapter.updateList(locationList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}