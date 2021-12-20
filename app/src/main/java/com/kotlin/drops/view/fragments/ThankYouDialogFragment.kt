package com.kotlin.drops.view.fragments

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.kotlin.drops.databinding.ActivityLoginBinding.inflate
import com.kotlin.drops.databinding.FragmentBokkiingBinding
import com.kotlin.drops.view.viewmodel.HomeViewModel


class ThankYouDialogFragment : DialogFragment() {

    private lateinit var binding: ThankYouDialogFragment
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ThankYouDialogFragment.inflate(inflater, container, false)
        return binding.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Close the dialog
        binding.closeImageButton.setOnClickListener {
            dismiss()
        }


    }


}