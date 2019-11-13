package com.mahmoudalyudeen.ambrosia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mahmoudalyudeen.ambrosia.R
import com.mahmoudalyudeen.ambrosia.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentHomeBinding.inflate(inflater)
        initView(binding)
        initEventObservers()
        return binding.root
    }

    private fun initView(binding: FragmentHomeBinding) {
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.entriesRecycler.adapter = EntriesAdapter()
        binding.nutrientsProgressRecycler.adapter = NutrientProgressAdapter(NutrientsProgressType.CIRCLE)
    }

    private fun initEventObservers() {
        homeViewModel.navigateAddEntry.observe(viewLifecycleOwner, Observer {
            it?.let {
                navigateAddEntry()
                homeViewModel.onNavigationAddEntryDone()
            }
        })
    }

    private fun navigateAddEntry() {
        if (findNavController().currentDestination?.id != R.id.fragment_home) return
        findNavController().navigate(R.id.navigate_add_entry)
    }
}
