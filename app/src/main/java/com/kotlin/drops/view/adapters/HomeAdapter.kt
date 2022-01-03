package com.kotlin.drops.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.kotlin.drops.R
import com.kotlin.drops.databinding.HomeItemLayoutBinding
import com.kotlin.drops.model.PatientInfo


class HomeAdapter(private val list: FragmentActivity) :


    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PatientInfo>() {

        override fun areItemsTheSame(oldItem: PatientInfo, newItem: PatientInfo): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: PatientInfo, newItem: PatientInfo): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<PatientInfo>) {
        differ.submitList(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {

        val binding =
            HomeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)

//        binding.patientInfoCardview.setOnClickListener {
//            findNavController().navigate(R.id.action_bokkiingFragment_to_thankYouDialogFragment)
//        }
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


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