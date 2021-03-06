package com.kotlin.drops.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.kotlin.drops.R
import com.kotlin.drops.databinding.HomeItemLayoutBinding
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.view.viewmodel.HomeViewModel


class HomeAdapter(val homeViewModel: HomeViewModel) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    // DiffUtil --> will keep old data and just change or add the new one

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PatientInfo>() {

        override fun areItemsTheSame(oldItem: PatientInfo, newItem: PatientInfo): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: PatientInfo, newItem: PatientInfo): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    // to give the differ our data (the list)

    fun submitList(list: List<PatientInfo>) {
        differ.submitList(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {

        val binding =
            HomeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)


    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.bind(item)

        // make the patient info card view clickable
        holder.binding.patientInfoCardview.setOnClickListener {
            // take Patient Info data to the the detail fragment
            homeViewModel.selectedItemMutableLiveData.postValue(item)
            holder.itemView.findNavController()
                .navigate(R.id.action_homeFragment_to_paitentInfoFragment)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    // home_item_layout items

    class HomeViewHolder(val binding: HomeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PatientInfo) {
            binding.paitentNameTextView.text = item.fullName
            binding.hospitalNameTextview.text = item.hospital
            binding.bloodGroupTextView.text = item.bloodGroup
            binding.leftBloodDonatedTextView.text = item.left.toString()
            binding.needBloodDonation.text = item.need.toString()

        }
    }

}