package com.kotlin.drops.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kotlin.drops.databinding.ThankyouDaialogLayoutBinding

class ThankYouDialog: DialogFragment() {

    private lateinit var binding: ThankyouDaialogLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ThankyouDaialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Close the dialog
        binding.closeImageButton.setOnClickListener {
            dismiss()
        }


    }
}