package com.kotlin.drops.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.drops.R
import com.kotlin.drops.model.PatientInfo

class HomeAdapter(private val list: List<PatientInfo>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {

        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val item = list[position]
        TODO("bind view with data")
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}