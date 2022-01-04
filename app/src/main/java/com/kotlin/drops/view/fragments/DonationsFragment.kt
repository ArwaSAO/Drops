package com.kotlin.drops.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.kotlin.drops.databinding.FragmentDonationsBinding
import com.kotlin.drops.databinding.FragmentHomeBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.reposetories.SHARED_PREF_FILE
import com.kotlin.drops.view.adapters.DonationAdapter
import com.kotlin.drops.view.adapters.HomeAdapter
import com.kotlin.drops.view.viewmodel.DonationsViewModel
import com.kotlin.drops.view.viewmodel.HomeViewModel


class DonationsFragment : Fragment() {


    private lateinit var binding: FragmentDonationsBinding
    private lateinit var donationAdapter: DonationAdapter
    private val donationViewModel: DonationsViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor
    private var allDonataitons = listOf<Donataitons>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this line only wrote in fragment
        setHasOptionsMenu(true)
        // get data
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        // edit data
        sharedPrefEditor = sharedPref.edit()

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

        donationAdapter = DonationAdapter(donationViewModel)
        binding.donationsRecyclerView.adapter = donationAdapter
        observers()
        // call request here because when we open the application we want response
        // event
        donationViewModel.callDonationsList()
//        donationViewModel.editDonation()
//        donationViewModel.deleteDonations()

    }


    fun observers() {

        donationViewModel.getDonationsLiveData.observe(viewLifecycleOwner, {

            binding.donatonsProgressBar.animate().alpha(0f).setDuration(1000)
            donationAdapter.submitList(it)
            allDonataitons = it
        })
    }






}