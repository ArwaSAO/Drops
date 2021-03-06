package com.kotlin.drops.view.fragments

import android.app.ProgressDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kotlin.drops.R
import com.kotlin.drops.databinding.FragmentHomeBinding
import com.kotlin.drops.model.PatientInfo
import com.kotlin.drops.view.adapters.HomeAdapter
import com.kotlin.drops.view.viewmodel.HomeViewModel

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var allPatientInfo = listOf<PatientInfo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // this line only wrote in fragment
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
        // setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeAdapter(homeViewModel)
        binding.homeRecyclerView.adapter = homeAdapter

        observers()
        homeViewModel.callPatientList()


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.search_app_bar, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

    }

    fun observers() {
        homeViewModel.getPatientInfoLiveData.observe(viewLifecycleOwner, {

            Log.d(TAG, "patient Info Live Data observers ")
            binding.homeProgressBar.animate().alpha(0f).setDuration(1000)
            homeAdapter.submitList(it)
            allPatientInfo = it

        })

        // handle the error
        homeViewModel.patientInfoErrorLiveData.observe(viewLifecycleOwner, { error ->
            error?.let {
                Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
                if (error == "Unauthorized")

                //to transfer from home fragment to PatientInfo fragment
                    findNavController().navigate(R.id.action_homeFragment_to_paitentInfoFragment)


                homeViewModel.patientInfoErrorLiveData.postValue(null)
            }
        })
    }


}