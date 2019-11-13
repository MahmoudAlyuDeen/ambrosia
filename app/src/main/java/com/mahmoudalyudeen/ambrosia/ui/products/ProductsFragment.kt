package com.mahmoudalyudeen.ambrosia.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mahmoudalyudeen.ambrosia.R
import com.mahmoudalyudeen.ambrosia.databinding.FragmentProductsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsFragment : Fragment() {

    private val productsViewModel: ProductsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentProductsBinding.inflate(inflater)
        initView(binding)
        initEventObservers()
        return binding.root
    }

    private fun initView(binding: FragmentProductsBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.productsViewModel = productsViewModel
        binding.productsRecycler.adapter = ProductsAdapter(ProductListener(productsViewModel::onProductClick))
        binding.productsToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun initEventObservers() {
        productsViewModel.navigateToPortions.observe(viewLifecycleOwner, Observer {
            it?.let {
                navigateToPortions(it)
                productsViewModel.onNavigationToEntryDone()
            }
        })
    }

    private fun navigateToPortions(productId: Int) {
        if (findNavController().currentDestination?.id != R.id.fragment_products) return
        findNavController().navigate(ProductsFragmentDirections.navigateSelectPortion(productId))
    }
}
