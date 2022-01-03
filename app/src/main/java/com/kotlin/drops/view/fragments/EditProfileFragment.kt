package com.kotlin.drops.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentEditProfileBinding
import com.kotlin.drops.databinding.FragmentProfileNew2Binding
import com.kotlin.drops.reposetories.SHARED_PREF_FILE
import com.kotlin.drops.view.viewmodel.ProfileViewModel


class EditProfileFragment : Fragment() {


    private lateinit var binding: FragmentEditProfileBinding
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
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()

        binding.saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_editProfileFragment_to_profileNewFragment)
        }
    }

    fun observers(){

        profileViewModel.donationsLiveData.observe(viewLifecycleOwner,{
            binding.fullNameEditText.text
            binding.phoneNumberEditText.text
            binding.ageEditText.text
            binding.bloodTypeEdittext.text
            binding.cityEditText.text

        })
    }


}