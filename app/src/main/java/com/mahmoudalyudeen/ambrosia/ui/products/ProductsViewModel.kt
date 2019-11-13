package com.mahmoudalyudeen.ambrosia.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudalyudeen.ambrosia.domain.Product
import com.mahmoudalyudeen.ambrosia.repo.ProductRepository

class ProductsViewModel(productRepository: ProductRepository) : ViewModel() {

    private val _products = productRepository.getProducts()
    val products: LiveData<List<Product>>
        get() = _products

    private val _navigateToPortions = MutableLiveData<Int>()
    val navigateToPortions: LiveData<Int>
        get() = _navigateToPortions

    fun onProductClick(product: Product) {
        _navigateToPortions.value = product.id
    }

    fun onNavigationToEntryDone() {
        _navigateToPortions.value = null
    }
}
