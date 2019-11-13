package com.mahmoudalyudeen.ambrosia.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mahmoudalyudeen.ambrosia.databinding.FragmentReportBinding
import com.mahmoudalyudeen.ambrosia.ui.home.NutrientProgressAdapter
import com.mahmoudalyudeen.ambrosia.ui.home.NutrientsProgressType
import org.koin.android.viewmodel.ext.android.viewModel

class ReportFragment : Fragment() {

    private val reportViewModel: ReportViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentReportBinding.inflate(inflater)
        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentReportBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.reportViewModel = reportViewModel
        reportViewModel.nutrientsProgress.observe(viewLifecycleOwner, Observer { })
        binding.reportRecyclerDetails.adapter = NutrientProgressAdapter(NutrientsProgressType.FULL)
        binding.reportToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

}