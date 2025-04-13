package com.mek.internshipproject.ui.fragments

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.mek.internshipproject.R
import com.mek.internshipproject.databinding.FragmentHomeBinding
import com.mek.internshipproject.model.Location
import com.mek.internshipproject.ui.adapters.ExpandableListAdapter
import com.mek.internshipproject.ui.viewModels.HomeViewModel
import com.mek.internshipproject.util.OnFavoriteClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private  var _binding : FragmentHomeBinding ?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var listViewAdapter :ExpandableListAdapter
    private var favoriteLocations : List<Location> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchCityLocations()
        observeCityList()
        observeFavorites()
        observeIsLoading()
        observeErorMessage()
        closeAllTabs()
        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.favorites_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
               return when(menuItem.itemId){
                   R.id.favoritesFragment -> {
                       Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_favoritesFragment)
                       true
                   }
                   else -> false
               }
            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)




    }




    private fun closeAllTabs() {
        binding.fabCloseAll.setOnClickListener {
            for (i in 0 until binding.ExpandableListView.expandableListAdapter.groupCount) {
                binding.ExpandableListView.collapseGroup(i)
            }
        }
    }

    private fun observeErorMessage() {
        viewModel.observeErorMessage().observe(viewLifecycleOwner,Observer{ message ->
            if (message.isNotEmpty()){
                binding.textViewErorMessage.visibility = View.VISIBLE
                binding.textViewErorMessage.text = message
            }else{
                binding.textViewErorMessage.visibility = View.GONE
            }
        })
    }

    private fun observeIsLoading() {
        viewModel.observeIsLoading().observe(viewLifecycleOwner,Observer{ isLoading ->
            if (isLoading) {
                binding.progressBarLoading.visibility = View.VISIBLE
                binding.ExpandableListView.visibility = View.GONE
            } else {
                binding.progressBarLoading.visibility = View.GONE
                binding.ExpandableListView.visibility = View.VISIBLE
            }
        })
    }

    private fun observeCityList() {
        viewModel.observeCityDataList().observe(viewLifecycleOwner, Observer { cities ->
            listViewAdapter = ExpandableListAdapter(requireContext(),
                cities,
                favoriteLocations,
                object : OnFavoriteClickListener{
                    override fun onFavoriteClick(location: Location,isFavorite : Boolean) {
                        if (isFavorite){
                            viewModel.deleteLocation(location)
                        }else{
                            viewModel.insertLocation(
                                description = location.description ?:"açıklama yok",
                                name = location.name ?: "isim yok",
                                image = location.image!!,
                                coordinates = location.coordinates!!,
                                id = location.id!!
                            )
                        }

                    }
                }
                )
            binding.ExpandableListView.setAdapter(listViewAdapter)
        })
    }

    private fun observeFavorites() {
        viewModel.getAllFavoriteLocations().observe(viewLifecycleOwner) { favorites ->
            favoriteLocations = favorites
            if (::listViewAdapter.isInitialized) {
                listViewAdapter.updateFavorites(favorites)
            }
        }
    }



}