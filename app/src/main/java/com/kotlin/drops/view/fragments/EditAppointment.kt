package com.kotlin.drops.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.EditApointmentLayoutBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.view.viewmodel.DonationsViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "EditAppointment"
class EditAppointment(val date: String,
                      val fullName: String,
                      val hospital: String,
                      val id: String,
                      val latitude: String,
                      val location:String,
                      val longitude: String,
                      val time: String,
                      val userId: String) : DialogFragment() {

    private lateinit var binding: EditApointmentLayoutBinding
    private val donationsViewModel: DonationsViewModel by activityViewModels()
    private lateinit var allDonataitons: Donataitons


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditApointmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        donationsViewModel.callDonationsList()
        observe()

        var date2 = binding.bookingDonationDate2.text
        var time2 =  binding.bookingDonationTime2.text


        binding.calenderImagebutton2.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(
                view.context,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    date2 = "" + dayOfMonth + " " + (monthOfYear +1).toString() + ", " + year
                },
                year,
                month,
                day
            )
            dpd.show()


        }

        binding.timeImageButton2.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                // display time text
                time2 = SimpleDateFormat("HH:mm a").format(calendar.time)
            }
            // time picker
            TimePickerDialog(
                view.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false
            ).show()

        }


        binding.confirmButton3.setOnClickListener {

        if (date2.isNotEmpty() && time2.isNotEmpty() ){

            val donation = Donataitons(
                date2.toString(),
                fullName,
                hospital,
                id,
                latitude,
                location,
                longitude,
                time2.toString(),
                userId)

            donationsViewModel.editDonation(donation)

        }



    }




}
    fun observe(){

        donationsViewModel.editDonationsLiveData.observe(viewLifecycleOwner, {
            it?.let{
                Log.d(TAG, "donations Info Live Data observers ")
                donationsViewModel.editDonationsLiveData.postValue(null)
                dismiss()
            }
        })

    }


                      }