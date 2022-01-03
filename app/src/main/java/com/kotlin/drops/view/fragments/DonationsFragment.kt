package com.kotlin.drops.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentBokkiingBinding
import com.kotlin.drops.databinding.FragmentDonationsBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.reposetories.SHARED_PREF_FILE
import com.kotlin.drops.view.adapters.DonationAdapter
import com.kotlin.drops.view.adapters.HomeAdapter
import com.kotlin.drops.view.viewmodel.BokkingViewModel
import com.kotlin.drops.view.viewmodel.DonationsViewModel


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

    fun observers() {


        donationViewModel.donationsLiveData.observe(viewLifecycleOwner, {
            binding.donatonsProgressBar.animate().alpha(0f).setDuration(1000)
            donationAdapter.submitList(it)
            allDonataitons = it


        })

    }






}