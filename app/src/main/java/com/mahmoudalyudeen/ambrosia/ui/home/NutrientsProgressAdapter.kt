package com.mahmoudalyudeen.ambrosia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudalyudeen.ambrosia.databinding.ItemNutrientProgressCircleBinding
import com.mahmoudalyudeen.ambrosia.databinding.ItemNutrientProgressFullBinding
import com.mahmoudalyudeen.ambrosia.databinding.ItemNutrientProgressMiniBinding
import com.mahmoudalyudeen.ambrosia.databinding.ItemNutrientProgressValueBinding
import com.mahmoudalyudeen.ambrosia.domain.NutrientProgress

private const val ITEM_VIEW_TYPE_CIRCLE = 0
private const val ITEM_VIEW_TYPE_FULL = 1
private const val ITEM_VIEW_TYPE_MINI = 2
private const val ITEM_VIEW_TYPE_VALUE = 3

enum class NutrientsProgressType(val viewType: Int) {
    CIRCLE(ITEM_VIEW_TYPE_CIRCLE),
    FULL(ITEM_VIEW_TYPE_FULL),
    VALUE(ITEM_VIEW_TYPE_VALUE),
    MINI(ITEM_VIEW_TYPE_MINI)
}

class NutrientProgressAdapter(private val type: NutrientsProgressType) :
    ListAdapter<NutrientProgress, RecyclerView.ViewHolder>(DiffCallBack) {

    override fun getItemViewType(position: Int): Int = type.viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_CIRCLE -> NutrientProgressCircleViewHolder.from(parent)
            ITEM_VIEW_TYPE_MINI -> NutrientProgressMiniViewHolder.from(parent)
            ITEM_VIEW_TYPE_VALUE -> NutrientProgressValueViewHolder.from(parent)
            ITEM_VIEW_TYPE_FULL -> NutrientProgressFullViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val nutrientProgress = getItem(position)
        val previousItem = if (position > 0) getItem(position - 1) else null
        val showCategoryHeader = previousItem?.nutrient?.category != nutrientProgress.nutrient.category
        val showDivider = showCategoryHeader && position > 0
        when (holder) {
            is NutrientProgressCircleViewHolder -> holder.bind(nutrientProgress)
            is NutrientProgressMiniViewHolder -> holder.bind(nutrientProgress)
            is NutrientProgressValueViewHolder -> holder.bind(nutrientProgress)
            is NutrientProgressFullViewHolder -> holder.bind(nutrientProgress, showCategoryHeader, showDivider)
        }
    }

    class NutrientProgressValueViewHolder(private var binding: ItemNutrientProgressValueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): NutrientProgressValueViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemNutrientProgressValueBinding.inflate(inflater, parent, false)
                return NutrientProgressValueViewHolder(binding)
            }
        }

        fun bind(nutrientProgress: NutrientProgress) {
            binding.nutrientProgress = nutrientProgress
            binding.executePendingBindings()
        }
    }

    class NutrientProgressMiniViewHolder(private var binding: ItemNutrientProgressMiniBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): NutrientProgressMiniViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemNutrientProgressMiniBinding.inflate(inflater, parent, false)
                return NutrientProgressMiniViewHolder(binding)
            }
        }

        fun bind(nutrientProgress: NutrientProgress) {
            binding.nutrientProgress = nutrientProgress
            binding.executePendingBindings()
        }
    }

    class NutrientProgressCircleViewHolder(private var binding: ItemNutrientProgressCircleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): NutrientProgressCircleViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemNutrientProgressCircleBinding.inflate(inflater, parent, false)
                return NutrientProgressCircleViewHolder(binding)
            }
        }

        fun bind(nutrientProgress: NutrientProgress) {
            binding.nutrientProgress = nutrientProgress
            binding.executePendingBindings()
        }
    }

    class NutrientProgressFullViewHolder(private var binding: ItemNutrientProgressFullBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): NutrientProgressFullViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemNutrientProgressFullBinding.inflate(inflater, parent, false)
                return NutrientProgressFullViewHolder(binding)
            }
        }

        fun bind(nutrientProgress: NutrientProgress, showCategoryHeader: Boolean, showDivider: Boolean) {
            binding.nutrientProgress = nutrientProgress
            binding.headerVisibility = if (showCategoryHeader) View.VISIBLE else View.GONE
            binding.dividerVisibility = if (showDivider) View.VISIBLE else View.GONE
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<NutrientProgress>() {
        override fun areItemsTheSame(oldItem: NutrientProgress, newItem: NutrientProgress): Boolean {
            return oldItem.nutrient.id == newItem.nutrient.id
        }

        override fun areContentsTheSame(oldItem: NutrientProgress, newItem: NutrientProgress): Boolean {
            return oldItem == newItem
        }
    }
}