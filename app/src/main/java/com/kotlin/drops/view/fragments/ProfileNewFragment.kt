package com.kotlin.drops.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentProfileNew2Binding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.reposetories.SHARED_PREF_FILE
import com.kotlin.drops.view.viewmodel.ProfileViewModel




class ProfileNewFragment : Fragment() {


    private lateinit var binding: FragmentProfileNew2Binding
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor



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
        binding = FragmentProfileNew2Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()

//        profileViewModel.editDonorInfo()
        profileViewModel.callDonorInfo()

        binding.editButton.setOnClickListener {

            findNavController().navigate(R.id.action_profileNewFragment_to_editProfileFragment)

        }
    }

    // assign coming data to the adapter

    fun observers() {


        profileViewModel.getDonorInfoLiveData.observe(viewLifecycleOwner, {

            binding.ageTextview.text
            binding.fullnameTextview.text
            binding.phoneNumber.text
            binding.bloodGroup.text

        })

    }

}


