package com.mek.internshipproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mek.internshipproject.databinding.PlacesListBinding
import com.mek.internshipproject.model.Location
import com.mek.internshipproject.ui.fragments.FavoritesFragmentDirections
import com.mek.internshipproject.util.OnFavoriteClickListener

class FavoritesAdapter(private val favoriteClickListener : OnFavoriteClickListener) : RecyclerView.Adapter<FavoritesAdapter.viewHolder>() {
    private var placeList = listOf<Location>()

    inner class viewHolder(val binding: PlacesListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var binding = PlacesListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
      return placeList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val location = placeList[position]
        val myBinding = holder.binding

        myBinding.textViewLocationName.text = location.name
        myBinding.imageViewFavoriteFull.visibility = View.VISIBLE
        myBinding.imageViewFavoriteEmpty.visibility = View.GONE
        myBinding.imageViewFavoriteFull.setOnClickListener {
            favoriteClickListener.onFavoriteClick(location,false)
        }


        myBinding.textViewLocationName.setOnClickListener {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToFavoritesDetailFragment(location)
            Navigation.findNavController(it).navigate(action)

        }
    }

    fun updateList(newList : List<Location>){
        placeList = newList
        notifyDataSetChanged()
    }

}