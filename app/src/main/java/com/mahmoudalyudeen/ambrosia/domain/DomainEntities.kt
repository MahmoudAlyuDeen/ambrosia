package com.mahmoudalyudeen.ambrosia.domain

import com.mahmoudalyudeen.ambrosia.util.getNutrientInEntries
import com.mahmoudalyudeen.ambrosia.util.round

data class Product(
    val id: Int,
    val name: String,
    val portions: List<Portion>,
    val nutrients: List<Nutrient>,
    val nutrientAmounts: Map<Nutrient, Double>
)

data class Entry(
    val id: Int,
    val product: Product,
    val portion: Portion,
    val portionCount: Double,
    val timestamp: Long
)

data class Nutrient(
    val id: Int,
    val name: String,
    val unit: String,
    val target: Double,
    val category: String,
    val glanceable: Boolean
) {
    val stringTarget = target.toInt().toString()
}

data class Portion(
    val id: Int,
    val unit: String,
    val weight: Double
) {
    val unitAndWeight = "$unit ($weight g)"
    override fun toString() = unitAndWeight
}

data class NutrientProgress(
    val nutrient: Nutrient,
    val target: Double,
    val current: Double,
    val percentage: Double
) {
    val currentAndUnit = "${current.toInt()} ${nutrient.unit}"
}

data class NutrientAmount(
    val nutrient: Nutrient,
    val amount: Double
)

fun List<Nutrient>.asNutrientsProgress(entries: List<Entry>?): List<NutrientProgress> {
    return if (entries == null) emptyList() else this.map {
        val current = getNutrientInEntries(entries, it)
        val percentage = (current / it.target) * 100
        NutrientProgress(
            nutrient = it,
            target = it.target.round(digits = 0),
            current = current.round(digits = 0),
            percentage = percentage.round(digits = 0)
        )
    }
}
