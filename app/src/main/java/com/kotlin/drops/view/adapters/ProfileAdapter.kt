package com.kotlin.drops.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.kotlin.drops.databinding.HomeItemLayoutBinding
import com.kotlin.drops.databinding.ProfileItemLayoutBinding
import com.kotlin.drops.model.Donataitons

private const val TAG = "ProfileAdapter"
class ProfileAdapter(private val list: List<Donataitons>) :


    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Donataitons>() {

        override fun areItemsTheSame(oldItem: Donataitons, newItem: Donataitons): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Donataitons, newItem: Donataitons): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<Donataitons>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileAdapter.ProfileViewHolder {

        val binding =
            ProfileItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileAdapter.ProfileViewHolder(binding)

    }


    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    class ProfileViewHolder(val binding: ProfileItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Donataitons) {

            binding.dopnationmontTextView.text = item.date
            binding.donationDateTextview.text= item.date
            binding.donationLocationTextview.text = item.location
            binding.timeDonationTextview.text = item.time
        }

    }

}