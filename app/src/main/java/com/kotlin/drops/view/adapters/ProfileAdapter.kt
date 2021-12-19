package com.kotlin.drops.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.drops.R
import com.kotlin.drops.model.Donataitons

class ProfileAdapter(private val list: List<Donataitons>) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileAdapter.ProfileViewHolder {

        return ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.profile_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {

        val item = list[position]
        TODO("bind view with data")
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}