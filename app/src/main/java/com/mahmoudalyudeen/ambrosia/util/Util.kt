package com.mahmoudalyudeen.ambrosia.util

import com.mahmoudalyudeen.ambrosia.domain.Entry
import com.mahmoudalyudeen.ambrosia.domain.Nutrient
import java.math.BigDecimal
import java.math.RoundingMode

fun getNutrientInEntries(_entries: List<Entry>, nutrient: Nutrient): Double =
    _entries.fold(initial = BigDecimal.valueOf(0.0)) { nutrientTotal, entry ->
        nutrientTotal + getNutrientInEntry(entry, nutrient)
    }.toDouble()

fun getNutrientInEntry(entry: Entry, nutrient: Nutrient): BigDecimal {
    val nutrientPerGram = entry.product.nutrientAmounts.getOrElse(nutrient) { 0.0 }
    return nutrientPerGram.toBigDecimal() * entry.portionCount.toBigDecimal() * entry.portion.weight.toBigDecimal()
}

fun Double.round(digits: Int) = toBigDecimal().setScale(digits, RoundingMode.UP).toDouble()

fun CharSequence.doubleValue(): Double {
    return if (isEmpty()) 0.0
    else try {
        toString().replace(",", "").toDouble()
    } catch (e: Exception) {
        0.0
    }
}