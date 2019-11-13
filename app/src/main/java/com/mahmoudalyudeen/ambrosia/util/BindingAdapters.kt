package com.mahmoudalyudeen.ambrosia.util

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.mahmoudalyudeen.ambrosia.domain.Entry
import com.mahmoudalyudeen.ambrosia.domain.NutrientProgress
import com.mahmoudalyudeen.ambrosia.domain.Product
import com.mahmoudalyudeen.ambrosia.ui.home.EntriesAdapter
import com.mahmoudalyudeen.ambrosia.ui.home.NutrientProgressAdapter
import com.mahmoudalyudeen.ambrosia.ui.products.ProductsAdapter

@BindingAdapter("entries")
fun bindRecyclerViewEntries(recyclerView: RecyclerView, entries: List<Entry>?) {
    entries?.let {
        val adapter = recyclerView.adapter as EntriesAdapter
        adapter.submitList(entries)
    }
}

@BindingAdapter("products")
fun bindRecyclerViewProducts(recyclerView: RecyclerView, products: List<Product>?) {
    products?.let {
        val adapter = recyclerView.adapter as ProductsAdapter
        adapter.submitList(products)
    }
}

@BindingAdapter("nutrientsProgress")
fun bindRecyclerViewNutrientsProgress(recyclerView: RecyclerView, nutrientsProgress: List<NutrientProgress>?) {
    nutrientsProgress?.let {
        val adapter = recyclerView.adapter as NutrientProgressAdapter
        adapter.submitList(nutrientsProgress)
    }
}

@BindingAdapter("nutrientProgress")
fun bindCircularProgressBarNutrientProgress(progressBar: CircularProgressBar, nutrientProgress: NutrientProgress) {
    val safePercentage = if (nutrientProgress.percentage >= 100) 100f else nutrientProgress.percentage.toFloat()
    progressBar.apply {
        setProgressWithAnimation(safePercentage, 300)
        progressMax = 100f
    }
}

@BindingAdapter("widthPercentage")
fun bindViewWidthPercentage(view: View, percentage: Double) {
    val percentageDecimal = if (percentage >= 100) 1f else (percentage / 100).toFloat()
    val constraintSet = ConstraintSet()
    val parent = view.parent as ConstraintLayout
    constraintSet.clone(parent)
    percentage.let { constraintSet.constrainPercentWidth(view.id, percentageDecimal) }
    constraintSet.applyTo(parent)
}
