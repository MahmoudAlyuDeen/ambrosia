package com.mahmoudalyudeen.ambrosia.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import com.mahmoudalyudeen.ambrosia.db.FoodDatabase
import com.mahmoudalyudeen.ambrosia.db.asDomainProduct
import com.mahmoudalyudeen.ambrosia.db.asDomainProducts
import com.mahmoudalyudeen.ambrosia.domain.Product

class ProductRepository(private val foodDatabase: FoodDatabase) {

    fun getProduct(productId: Int): LiveData<Product?> =
        map(foodDatabase.productDao.getProduct(productId)) { it.asDomainProduct() }

    fun getProducts(): LiveData<List<Product>> =
        map(foodDatabase.productDao.getProducts()) { it.asDomainProducts() }
}