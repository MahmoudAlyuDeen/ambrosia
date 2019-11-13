package com.mahmoudalyudeen.ambrosia.ui.portion

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior.from
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mahmoudalyudeen.ambrosia.R
import com.mahmoudalyudeen.ambrosia.databinding.FragmentPortionBinding
import com.mahmoudalyudeen.ambrosia.domain.Portion
import com.mahmoudalyudeen.ambrosia.ui.MainActivity
import com.mahmoudalyudeen.ambrosia.ui.home.NutrientProgressAdapter
import com.mahmoudalyudeen.ambrosia.ui.home.NutrientsProgressType
import com.mahmoudalyudeen.ambrosia.util.NoFilterArrayAdapter
import com.mahmoudalyudeen.ambrosia.util.PORTION_COUNT
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class PortionFragment : BottomSheetDialogFragment() {

    private val portionViewModel: PortionViewModel by viewModel {
        val fragmentArgs = arguments?.let { PortionFragmentArgs.fromBundle(it) }
        parametersOf(fragmentArgs?.productId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPortionBinding.inflate(inflater, container, false)
        initDialog()
        initView(binding)
        initEventObservers()
        initPortionSizeMenu(binding)
        initPortionCountMenu(binding)
        return binding.root
    }

    private fun initDialog() {
        dialog?.setOnShowListener {
            val bottomSheet = (dialog as BottomSheetDialog).findViewById<View>(R.id.design_bottom_sheet)
            val bottomSheetBehavior = from(bottomSheet)
            bottomSheetBehavior.apply {
                peekHeight = (resources.displayMetrics.heightPixels * 0.5).toInt()
            }
        }
    }

    private fun initView(binding: FragmentPortionBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.portionViewModel = portionViewModel
        binding.portionRecyclerDetails.adapter = NutrientProgressAdapter(NutrientsProgressType.VALUE)
        portionViewModel.nutrientsProgress.observe(viewLifecycleOwner, Observer {})
        if (activity is MainActivity) {
            (activity as MainActivity).setToolbar(binding.portionToolbar)
        }
        portionViewModel.product.observe(viewLifecycleOwner, Observer {
            activity?.title = it?.name
        })
    }

    private fun initEventObservers() {
        portionViewModel.eventNavigateBack.observe(viewLifecycleOwner, Observer {
            it?.let {
                navigateBack(3)
                portionViewModel.onNavigateBackDone()
            }
        })
    }

    private fun initPortionSizeMenu(binding: FragmentPortionBinding) {
        portionViewModel.product.observe(viewLifecycleOwner, Observer {
            it?.let {
                val adapter = NoFilterArrayAdapter(
                    binding.portionSizeTextView.context,
                    android.R.layout.simple_list_item_1,
                    it.portions
                )
                binding.portionSizeTextView.setAdapter(adapter)
                binding.portionSizeTextView.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        val portion: Portion? = adapter.getItem(position)
                        portion?.let { portionViewModel.onPortionSelected(portion.id) }
                    }
            }
        })
    }

    private fun initPortionCountMenu(binding: FragmentPortionBinding) {
        val adapter = NoFilterArrayAdapter(
            binding.portionCountTextView.context,
            android.R.layout.simple_list_item_1,
            PORTION_COUNT
        )
        binding.portionCountTextView.setAdapter(adapter)
        binding.portionCountTextView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val portionCount: Double? = adapter.getItem(position)
                portionCount?.let { portionViewModel.onPortionCountChanged(portionCount) }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_portion, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navigateBack(1); return true
            }
            R.id.action_confirm_portion -> {
                portionViewModel.onConfirmClick(); return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateBack(count: Int) {
        if (findNavController().currentDestination?.id != R.id.fragment_portion) return
        for (i in 1..count) {
            findNavController().navigateUp()
        }
    }
}