package com.kotlin.drops.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentPaitentInfoBinding
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.view.viewmodel.HomeViewModel
import java.io.ByteArrayOutputStream


//==============================================================================================//

private const val TAG = "PaitentInfoFragment"

class PatientInfoFragment : Fragment() {

    private lateinit var binding: FragmentPaitentInfoBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor
    private lateinit var allPatientInfo: PatientInfo

//==============================================================================================//


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//         this line only wrote in fragment
        setHasOptionsMenu(true)

    }

//==============================================================================================//


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPaitentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


//================================================================================================//


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.callPatientList()
        observers()

        // book button take the user to the booking fragment
        // to choose Date & Time for the next donation

        binding.bookButton.setOnClickListener {

            findNavController().navigate(R.id.action_paitentInfoFragment_to_bokkiingFragment)

        }


//============================================================================================//


        // map button go to google map to show the location of the
        // hospital in order the user can get the location easily..


        binding.mapButton.setOnClickListener {

            // assign lat & lng to the map button to find the location
            val hospitalLocation = allPatientInfo
            val Uri =
                Uri.parse(
                    "google.navigation:q=" +
                            " ${hospitalLocation.latitude},${hospitalLocation.longitude}"
                )
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)


        }


//=============================================================================================//


        // share button give the user a choice to send the patient information to a friend

        binding.shareButton.setOnClickListener {
            val fullName = allPatientInfo.fullName
            val bloodGroup = allPatientInfo.bloodGroup
            val diagnosis = allPatientInfo.diagnosis
            val location = allPatientInfo.location
            val hospital = allPatientInfo.hospital
            val need = allPatientInfo.need
            val left = allPatientInfo.left
            val patientId = allPatientInfo.userId


            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            // shared text
            intent.putExtra(
                Intent.EXTRA_TEXT, " Even drop,s could save live,s let help our patient....\n " +
                        "FullName: $fullName\n   PatientId: $patientId\n  Blood Group: $bloodGroup\n  Diagnosis: $diagnosis " +
                        " Location: $location\n  Hospital: $hospital\n  Need: $need\n  Left: $left"
            )

            startActivity(Intent.createChooser(intent, "Share Link"))
        }


    }


//===============================================================================================//


    fun observers() {
        homeViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, {

            Log.d(TAG, "patient Info Live Data observers ")
            allPatientInfo = it
            binding.paitentNameTextview.text = it.fullName
            binding.paitentBloodTypeTextview.text = it.bloodGroup
            binding.paitentIdTextview.text = it.userId.toString()
            binding.paitentDiagnosisTextview.text = it.diagnosis
            binding.locationTextview.text = it.location
            binding.hospitalTextview.text = it.hospital
            binding.paitentLeftTextView.text = it.left.toString()
            binding.paitentNeedTextview.text = it.need.toString()
        })
    }

}

//===============================================================================================//