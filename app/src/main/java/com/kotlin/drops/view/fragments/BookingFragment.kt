package com.kotlin.drops.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.oAuthProvider
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
    private val homeViewModel: HomeViewModel by activityViewModels()
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

    //===========================================================================================//

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observers()
        homeViewModel.callPatientList()


        // code for pick date of the donation

        binding.calenderImagebutton.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(
                view.context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in TextView

                    binding.bookingDonationDate.text =
                        "" + dayOfMonth + " " + (monthOfYear + 1).toString() + ", " + year
                },
                year,
                month,
                day
            )
            dpd.show()
        }


        // code for pick time donation

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


        // confirm appointment
        binding.confirmButton.setOnClickListener {

            // put condition that the user could not book appointment if he is not choose Date & time
            // else if he didn't choose Date & Time a short message "Pick Date & Time for your next donation"

            var date1 = binding.bookingDonationDate.text
            var time1 = binding.bookingDonationTime.text

            if (date1.isNotEmpty() && time1.isNotEmpty()) {
                donationsViewModel.addDonations(allPatientInfo, date1.toString(), time1.toString())
                val thankDialog = ThankYouDialog()
                thankDialog.show(childFragmentManager, "thank_you")

            }


            // else part

            else {
                Toast.makeText(
                    requireActivity(),
                    "Pick Date & Time for your next donation",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    //============================================================================================//




    fun observers() {

        donationsViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, {

            Log.d(TAG, "donations Info Live Data observers ")
            allDonataitons = it
            binding.bookingDonationDate.text = it.date
            binding.bookingDonationTime.text = it.time
        })

        homeViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, {

            Log.d(TAG, "patient Info Live Data observers ")
            allPatientInfo = it
            binding.bookingDontationHospital.text = it.hospital
            binding.cityTextview.text = it.location

        })
    }
}