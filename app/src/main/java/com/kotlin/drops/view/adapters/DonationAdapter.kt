package com.kotlin.drops.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.kotlin.drops.R
import com.kotlin.drops.databinding.DonationsItemLayoutBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.view.viewmodel.DonationsViewModel

private const val TAG = "ProfileAdapter"
class DonationAdapter( val donationsViewModel: DonationsViewModel) : RecyclerView.Adapter<DonationAdapter.DonationViewHolder>() {

    // DiffUtil --> will keep old data and just change or add the new one

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Donataitons>() {

        override fun areItemsTheSame(oldItem: Donataitons, newItem: Donataitons): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Donataitons, newItem: Donataitons): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    // to give the differ our data (the list)

    fun submitList(list: List<Donataitons>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DonationAdapter.DonationViewHolder {

        val binding =
            DonationsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DonationAdapter.DonationViewHolder(binding)

    }


    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.bind(item)
        holder.binding.apointmentButton.setOnClickListener {

            donationsViewModel.selectedItemMutableLiveData.postValue(item)
            holder.itemView.findNavController()
                .navigate(R.id.action_donationsFragment_to_bokkiingFragment)
        }

        holder.binding.deleteButton.setOnClickListener {

            var listt = mutableListOf<Donataitons>()
            listt.addAll(differ.currentList)
            listt.remove(item)
            differ.submitList(listt.toList())
            donationsViewModel.deleteDonations(item)


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    class DonationViewHolder(val binding: DonationsItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Donataitons) {

            binding.dopnationmontTextView.text = item.date
            binding.donationDateTextview.text= item.date
            binding.donationLocationTextview.text = item.location
            binding.timeDonationTextview.text = item.time
        }
    }
}