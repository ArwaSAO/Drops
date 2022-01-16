package com.kotlin.drops.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentProfileNew2Binding
import com.kotlin.drops.model.DonorInfo
import com.kotlin.drops.view.identity.LoginActivity
import com.kotlin.drops.view.identity.sharedPreferEdit
import com.kotlin.drops.view.identity.sharedPreferences
import com.kotlin.drops.view.main.SHARED_PREF_FILE
import com.kotlin.drops.view.viewmodel.ProfileViewModel




class ProfileNewFragment : Fragment() {


    private lateinit var binding: FragmentProfileNew2Binding
    private val profileViewModel: ProfileViewModel by activityViewModels()




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
        binding = FragmentProfileNew2Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()

        profileViewModel.callDonorInfo()

        // add donor Info
        binding.editButton.setOnClickListener {

            findNavController().navigate(R.id.action_profileNewFragment_to_editProfileFragment)

        }


        // logout button
        binding.logoutImageButton.setOnClickListener {

            //sign out from firebase
            FirebaseAuth.getInstance().signOut()


            // sign out from sharedPreferences
            sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
            sharedPreferEdit= sharedPreferences.edit()
            sharedPreferEdit.clear()
            sharedPreferEdit.commit()

            // when the user logout it will return to Login activity
            val intent= Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)

            requireActivity().finish()

        }
    }

    // assign coming data to the adapter

    fun observers() {


        profileViewModel.donorInfoLiveData.observe(viewLifecycleOwner, {

            binding.ageTextview.text
            binding.fullnameTextview.text
            binding.phoneNumber.text
            binding.bloodGroup.text
            binding.cityTextView.text

        })

    }

}


