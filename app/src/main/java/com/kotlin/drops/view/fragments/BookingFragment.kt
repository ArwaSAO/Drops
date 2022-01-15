package com.kotlin.drops.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentBokkiingBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.view.viewmodel.DonationsViewModel
import com.kotlin.drops.view.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "BookingFragment"

class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBokkiingBinding
    private val donationsViewModel: DonationsViewModel by activityViewModels()
    private val homeViewModel:HomeViewModel by activityViewModels()
    private lateinit var allPatientInfo: PatientInfo
    private lateinit var allDonataitons: Donataitons



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBokkiingBinding.inflate(inflater, container, false)
        return binding.root


    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observers()
        homeViewModel.callPatientList()


        binding.calenderImagebutton.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(
                requireActivity(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    binding.bookingDonationDate.setText("" + dayOfMonth + " " + month + ", " + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        binding.timeImageButton.setOnClickListener {

            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                // display time text
                binding.bookingDonationTime.text = SimpleDateFormat("HH:mm a").format(calendar.time)
            }
            // time picker
            TimePickerDialog(
                view.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false
            ).show()
        }

        binding.confirmButton.setOnClickListener {

            donationsViewModel.addDonations(allPatientInfo,"","")
            findNavController().navigate(R.id.action_bokkiingFragment_to_donationsFragment)

        }
    }

    fun observers() {

        donationsViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner,{

            Log.d(TAG, "donations Info Live Data observers ")
            allDonataitons = it
            binding.bookingDonationDate.text = it.date
            binding.bookingDonationTime.text = it.time
        })

        homeViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner,{

            Log.d(TAG, "patient Info Live Data observers ")
            allPatientInfo = it
            binding.bookingDontationHospital.text = it.hospital
            binding.cityTextview.text = it.location

        })
    }
}