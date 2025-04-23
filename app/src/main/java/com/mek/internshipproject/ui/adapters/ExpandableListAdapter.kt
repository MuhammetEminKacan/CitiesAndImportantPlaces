package com.mek.internshipproject.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.mek.internshipproject.R
import com.mek.internshipproject.model.Data
import com.mek.internshipproject.model.Location
import com.mek.internshipproject.ui.activities.MapsActivity
import com.mek.internshipproject.ui.fragments.HomeFragmentDirections
import com.mek.internshipproject.util.OnFavoriteClickListener

class ExpandableListAdapter internal constructor(
    private val context: Context,
    private val cityList : List<Data>,
    private var favoriteLocations: List<Location>,
    private val favoriteClickListener : OnFavoriteClickListener
) : BaseExpandableListAdapter(){
    override fun getGroupCount(): Int {
       return cityList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return  cityList[groupPosition].locations?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return cityList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Location {
        return  cityList[groupPosition].locations?.get(childPosition) ?: Location(null,"bilinmeyen lokasyon",0,"","")
    }

    override fun getGroupId(groupPosition: Int): Long {
        return  groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
       return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var view = convertView
        val cityData =getGroup(groupPosition) as Data
        val cityTitle = cityData.city ?: "bilinmeyen şehir"
        if (view == null){
            val inflater =context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view =inflater.inflate(R.layout.citys_list,null)
        }
        val cityTextView =view?.findViewById<TextView>(R.id.textViewCityName)
        val openPlaceImageView = view?.findViewById<ImageView>(R.id.imageViewOpenImportantPlacesInTheCity)
        val closePlaceImageView = view?.findViewById<ImageView>(R.id.imageViewCloseImportantPlacesInTheCity)
        val goToMapsImageView = view?.findViewById<ImageView>(R.id.imageViewGoToMaps)

        cityTextView?.text = cityTitle

        val expandableListView = parent as ExpandableListView

        view?.setOnClickListener(null)


        if (cityData.locations.isNullOrEmpty()) {
            openPlaceImageView?.visibility = View.GONE
            closePlaceImageView?.visibility = View.GONE
            goToMapsImageView?.visibility = View.GONE
        } else {
            if (isExpanded) {
                openPlaceImageView?.visibility = View.INVISIBLE
                closePlaceImageView?.visibility = View.VISIBLE
            } else {
                openPlaceImageView?.visibility = View.VISIBLE
                closePlaceImageView?.visibility = View.INVISIBLE
            }
        }

        openPlaceImageView?.setOnClickListener {
            expandableListView.expandGroup(groupPosition,true)
        }

        closePlaceImageView?.setOnClickListener {
            expandableListView.collapseGroup(groupPosition)
        }

        goToMapsImageView?.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("city_name", cityTitle)

            val locations = cityData.locations ?: emptyList()
            intent.putExtra("locations_list", ArrayList(locations))

            context.startActivity(intent)
        }

        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var view = convertView
        var locationData =getChild(groupPosition,childPosition) as Location
        var locationTitle = locationData.name ?: "bilinmeyen lokasyon"

        if (view == null){
            val inflater =context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view =inflater.inflate(R.layout.places_list,null)
        }

        view?.setOnClickListener(null)

        val locationTextView =view?.findViewById<TextView>(R.id.textViewLocationName)
        val favEmpty = view?.findViewById<ImageView>(R.id.imageViewFavoriteEmpty)
        val favFull = view?.findViewById<ImageView>(R.id.imageViewFavoriteFull)

        locationTextView?.setText(locationTitle)
        val isFavorite = favoriteLocations.any{it.id == locationData.id}

        favEmpty?.setOnClickListener {
            favoriteClickListener.onFavoriteClick(locationData,false)
        }

        favFull?.setOnClickListener {
            favoriteClickListener.onFavoriteClick(locationData, true)
        }

        favEmpty?.visibility = if (isFavorite) View.GONE else View.VISIBLE
        favFull?.visibility = if (isFavorite) View.VISIBLE else View.GONE

        view?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFavoritesDetailFragment(locationData)
            Navigation.findNavController(it).navigate(action)
        }

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    fun updateFavorites(newFavorites: List<Location>) {
        this.favoriteLocations = newFavorites
        notifyDataSetChanged()
    }
}