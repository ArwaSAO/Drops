package com.kotlin.drops.view.fragments

import android.content.Context
import android.content.SharedPreferences
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
import com.kotlin.drops.databinding.FragmentHomeBinding
import com.kotlin.drops.databinding.FragmentPaitentInfoBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.reposetories.SHARED_PREF_FILE
import com.kotlin.drops.view.adapters.HomeAdapter
import com.kotlin.drops.view.viewmodel.HomeViewModel
import com.kotlin.drops.view.viewmodel.PatientInfoViewModel

private const val TAG = "PaitentInfoFragment"
class PaitentInfoFragment : Fragment() {

    private lateinit var binding: FragmentPaitentInfoBinding
    private val patientInfoViewModel: PatientInfoViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor
    private var allPatientInfo = listOf<PatientInfo>()



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
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPaitentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        patientInfoViewModel.callPatientList()
        observers()


        binding.BookingButton.setOnClickListener {

            findNavController().navigate(R.id.action_paitentInfoFragment_to_bokkiingFragment)

        }
    }

    private fun observers() {
        patientInfoViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, {

            Log.d(TAG, "patient Info Live Data observers ")
            allPatientInfo = listOf(it)
            binding.paitentNameTextview.text= it.fullName
            binding.paitentIdTextview.text = it.userId.toString()
            binding.paitentDiagnosisTextview.text = it.diagnosis
            binding.locationTextview.text = it.location
            binding.hospitalTextview.text = it.hospital
            binding.paitentLeftTextView.text = it.left.toString()
            binding.paitentNeedTextview.text = it.need.toString()

        })
    }

}