package com.kotlin.drops.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentBokkiingBinding
import com.kotlin.drops.databinding.FragmentPaitentInfoBinding
import com.kotlin.drops.model.Donataitons
import com.kotlin.drops.view.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

class BokkiingFragment : Fragment() {

    private lateinit var binding: FragmentBokkiingBinding

    

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

        binding.mapLocation.setOnClickListener {

            val gmapsIntentURI = Uri.parse("https://www.google.com/maps/place/Kingdom+Tower/@24.7113877," + "46.6722064," +
                    "17z/data=!3m1!4b1!4m5!3m4!1s0x3e2f03280e046f99:0x37737eab160a212!8m2!3d24.7113828!4d46.6743951")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmapsIntentURI)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }

        val dateRangePicker = DatePickerDialog(requireActivity())

        binding.calenderImagebutton.setOnClickListener {
            dateRangePicker.setButton(DialogInterface.BUTTON_POSITIVE, "OK") { _, _ ->
                // set the date text
////                booking_donation_date.setText("${dateRangePicker.datePicker.year}/" +
//                        "${dateRangePicker.datePicker.month+1}/" +
//                        "${dateRangePicker.datePicker.dayOfMonth}")
            }

        }



        binding.timeImageButton.setOnClickListener {

            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                // display time text
//                booking_donation_time.setText( SimpleDateFormat("HH:mm a").format(calendar.time))
            }
            // time picker
            TimePickerDialog(view.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false).show()


        }

        binding.BookingButton.setOnClickListener {

            findNavController().navigate(R.id.action_bokkiingFragment_to_thankYouDialogFragment)



        }

    }


}