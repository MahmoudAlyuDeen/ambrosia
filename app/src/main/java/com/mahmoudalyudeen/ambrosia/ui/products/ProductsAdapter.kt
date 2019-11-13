package com.mahmoudalyudeen.ambrosia.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudalyudeen.ambrosia.databinding.ItemProductBinding
import com.mahmoudalyudeen.ambrosia.domain.Product

class ProductsAdapter(private val productListener: ProductListener) :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, productListener)
    }

    class ProductViewHolder(private var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, productListener: ProductListener) {
            binding.product = product
            binding.productListener = productListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}

class ProductListener(private val onClickListener: (product: Product) -> Unit) {
    fun onClick(product: Product) {
        onClickListener(product)
    }
}