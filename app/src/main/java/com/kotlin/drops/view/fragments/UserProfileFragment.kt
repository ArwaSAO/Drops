package com.kotlin.drops.view.fragments

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentUserProfileBinding
//import com.kotlin.drops.databinding.FragmentUserProfileBinding
import com.kotlin.drops.model.UserProfile
import com.kotlin.drops.view.identity.LoginActivity
import com.kotlin.drops.view.identity.sharedPreferEdit
import com.kotlin.drops.view.identity.sharedPreferences
import com.kotlin.drops.view.main.SHARED_PREF_FILE
import com.kotlin.drops.view.viewmodel.UserProfileViewModel


private const val TAG = "UserProfile"

class UserProfileFragment : Fragment() {


    private lateinit var binding: FragmentUserProfileBinding
    private val userProfileViewModel: UserProfileViewModel by activityViewModels()
    private var userProfile = UserProfile()


    //=======================================================================================//


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

//===============================================================================================//


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            // edit user data to the firestore

            binding.editButton.setOnClickListener {
                if (binding.editButton.isChecked) {
                    binding.fullNameEditText2.isEnabled = true
                    binding.phoneNumberEditText2.isEnabled = true
                    binding.ageEdittext2.isEnabled = true
                    binding.bloodGroupEdittext2.isEnabled = true
                    binding.cityEditText2.isEnabled = true

                } else {
                    binding.fullNameEditText2.isEnabled = false
                    binding.phoneNumberEditText2.isEnabled = false
                    binding.ageEdittext2.isEnabled = false
                    binding.bloodGroupEdittext2.isEnabled = false
                    binding.cityEditText2.isEnabled = false

                    saveEdit() // save change


                }

            }
        //======================================================================================//


                // logout button
                binding.logoutButton.setOnClickListener {
                  MaterialAlertDialogBuilder(
                      requireActivity(),android.R.style.ThemeOverlay_Material_Dialog_Alert
                  )
                      .setMessage("Are you Sure you want to logout?")
                      .setNegativeButton("No"){
                          _,_ ->
                      }
                      .setPositiveButton("yes"){
                          _,_ ->

                          //sign out from firebase
                          FirebaseAuth.getInstance().signOut()

                          // sign out from sharedPreferences
                          sharedPreferences =
                              requireActivity().getSharedPreferences(
                                  SHARED_PREF_FILE,
                                  Context.MODE_PRIVATE
                              )
                          sharedPreferEdit = sharedPreferences.edit()
                          sharedPreferEdit.clear()
                          sharedPreferEdit.commit()

                          // when the user logout it will return to Login activity
                          val intent = Intent(requireActivity(), LoginActivity::class.java)
                          startActivity(intent)

                          requireActivity().finish()
                      }.show()
                }

                // call observers to get the view
                observers()

                // get user info
                userProfileViewModel.getUser()
            }


        //==============================================================================================//


    fun saveEdit() {

        userProfile.apply {

            FullName = binding.fullNameEditText2.text.toString()
            PhoneNumber = binding.phoneNumberEditText2.text.toString()
            Age = binding.ageEdittext2.text.toString()
            BloodGroup = binding.bloodGroupEdittext2.text.toString()
            City = binding.cityEditText2.text.toString()

            userProfileViewModel.save(this)

        }
    }


    fun observers() {

        // get user info live data

        userProfileViewModel.getUserLiveData.observe(viewLifecycleOwner, {

            userProfile = it
            binding.ageEdittext2.setText(it.Age)
            binding.fullNameEditText2.setText(it.FullName)
            binding.phoneNumberEditText2.setText(it.PhoneNumber)
            binding.bloodGroupEdittext2.setText(it.BloodGroup)
            binding.cityEditText2.setText(it.City)
            Log.d(ContentValues.TAG, it.toString())


        })

        //add profile live data

//        userProfileViewModel.saveUserLiveData.observe(viewLifecycleOwner, {
//
//            userProfile = it
//            binding.ageEdittext2.setText(it.Age)
//            binding.fullNameEditText2.setText(it.FullName)
//            binding.phoneNumberEditText2.setText(it.PhoneNumber)
//            binding.bloodGroupEdittext2.setText(it.BloodGroup)
//            binding.cityEditText2.setText(it.City)
//            Log.d(ContentValues.TAG, it.toString())
//        })


    }}




