package com.kotlin.drops.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentDonationsBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.view.adapters.DonationAdapter
import com.kotlin.drops.view.viewmodel.DonationsViewModel

private const val TAG = "DonationsFragment"

class DonationsFragment : Fragment() {


    private lateinit var binding: FragmentDonationsBinding
    private lateinit var donationAdapter: DonationAdapter
    private val donationViewModel: DonationsViewModel by activityViewModels()
    private var allDonations = listOf<Donataitons>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this line only wrote in fragment
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDonationsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        donationAdapter = DonationAdapter(requireActivity(), donationViewModel)
        binding.donationsRecyclerView.adapter = donationAdapter
        observers()
        donationViewModel.callDonationsList()
    }



   //============================================================================================//



    fun observers() {

        donationViewModel.getDonationsLiveData.observe(viewLifecycleOwner, {

            Log.d(TAG, "patient Info Live Data observers ")
            binding.donatonsProgressBar.animate().alpha(0f).setDuration(1000)
            donationAdapter.submitList(it)
            allDonations = it

        })

        // handle the error
        donationViewModel.donationsErrorLiveData.observe(viewLifecycleOwner, { error ->
            error?.let {
                Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
                if (error == "Unauthorized")

                    donationViewModel.donationsErrorLiveData.postValue(null)
            }
        })


    }


}