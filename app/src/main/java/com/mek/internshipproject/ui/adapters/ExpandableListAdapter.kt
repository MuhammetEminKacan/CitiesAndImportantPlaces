package com.mek.internshipproject.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.mek.internshipproject.R
import com.mek.internshipproject.model.Data
import com.mek.internshipproject.model.Location

class ExpandableListAdapter internal constructor(
    private val context: Context,
    private val cityList : List<Data>
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
        cityTextView?.setText(cityTitle)
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
        val locationTextView =view?.findViewById<TextView>(R.id.textViewLocationName)
        locationTextView?.setText(locationTitle)
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}