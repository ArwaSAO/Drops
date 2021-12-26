package com.kotlin.drops.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentProfileBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.reposetories.SHARED_PREF_FILE
import com.kotlin.drops.view.adapters.ProfileAdapter
import com.kotlin.drops.view.viewmodel.ProfileViewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileAdapter: ProfileAdapter
    private val profileViewModel: ProfileViewModel by activityViewModels()
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
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileAdapter = ProfileAdapter(profileViewModel)
        binding.recyclerView2.adapter = profileAdapter
        // then add it here
        // observe
        observers()
        // call request here because when we open the application we want response
        // event
        profileViewModel.callDonations()
    }

    // assign coming data to the adapter
    fun observers() {
        profileViewModel.donationsLiveData.observe(viewLifecycleOwner, {
//            binding.productsProgressBar.animate().alpha(0f).setDuration(1000)
            profileAdapter.submitList(it)
            allDonataitons = it
            binding.progressBar.animate().alpha(1f)

        })
        // handle the error
        profileViewModel.donationsErrorLiveData.observe(viewLifecycleOwner, { error ->
            error?.let {
                Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
                if (error == "Unauthorized")
                    findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                profileViewModel.donationsErrorLiveData.postValue(null)
            }

        })
    }




}