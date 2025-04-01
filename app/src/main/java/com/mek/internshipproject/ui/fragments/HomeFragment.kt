package com.mek.internshipproject.ui.fragments

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mek.internshipproject.R
import com.mek.internshipproject.databinding.FragmentHomeBinding
import com.mek.internshipproject.ui.adapters.ExpandableListAdapter
import com.mek.internshipproject.ui.viewModels.HomeViewModel

class HomeFragment : Fragment() {
    private  var _binding : FragmentHomeBinding ?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var listViewAdapter :ExpandableListAdapter

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
        observeIsLoading()
        observeErorMessage()


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
            binding.progressBarLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    private fun observeCityList() {
        viewModel.observeCityDataList().observe(viewLifecycleOwner, Observer { cities ->
            listViewAdapter = ExpandableListAdapter(requireContext(),cities)
            binding.ExpandableListView.setAdapter(listViewAdapter)
        })
    }

}