package com.kotlin.drops.view.adapters

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.kotlin.drops.R
import com.kotlin.drops.databinding.DonationsItemLayoutBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.view.fragments.EditAppointment
import com.kotlin.drops.view.fragments.ThankYouDialog
import com.kotlin.drops.view.viewmodel.DonationsViewModel

private const val TAG = "ProfileAdapter"


class DonationAdapter(val context: Context, val donationsViewModel: DonationsViewModel) :
    RecyclerView.Adapter<DonationAdapter.DonationViewHolder>() {


    private lateinit var allDonations: Donataitons

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


        // edit appointment live data from api
        holder.binding.apointmentButton.setOnClickListener {

            val manager = (context as AppCompatActivity).supportFragmentManager
            val editAppointment = EditAppointment(
                item.date, item.fullName, item.hospital, item.id,
                item.latitude, item.location, item.longitude, item.time, item.userId
            )
            editAppointment.show(manager, "editAppointment")

        }

        // this function is for delete appointment  live data from api
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


    // donation_item_layout items

    class DonationViewHolder(val binding: DonationsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Donataitons) {

            binding.dopnationmontTextView.text = item.date
            binding.donationDateTextview.text = item.time
            binding.donationLocationTextview.text = item.hospital
            binding.patientNameTextview.text = item.fullName

        }
    }
}