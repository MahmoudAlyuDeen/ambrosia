package com.mahmoudalyudeen.ambrosia.db

import androidx.room.Junction
import androidx.room.Relation
import com.mahmoudalyudeen.ambrosia.domain.Entry
import com.mahmoudalyudeen.ambrosia.domain.Product

data class QueryProductWithData(
    val productId: Int,
    val name: String,
    @Relation(
        entity = DatabasePortion::class,
        parentColumn = "productId",
        entityColumn = "productId"
    )
    val portions: List<DatabasePortion>,
    @Relation(
        parentColumn = "productId",
        entityColumn = "nutrientId",
        associateBy = Junction(
            value = ProductNutrientJoin::class,
            parentColumn = "productId",
            entityColumn = "nutrientId"
        )
    )
    val nutrients: List<DatabaseNutrient>,
    @Relation(
        entity = ProductNutrientJoin::class,
        parentColumn = "productId",
        entityColumn = "productId"
    )
    val nutrientJoins: List<ProductNutrientJoin>
)

data class QueryEntryWithData(
    val entryId: Int,
    val productId: Int,
    val portionId: Int,
    val portionCount: Double,
    val timestamp: Long,
    @Relation(
        entity = DatabaseProduct::class,
        parentColumn = "productId",
        entityColumn = "productId"
    )
    val product: QueryProductWithData,
    @Relation(
        entity = DatabasePortion::class,
        parentColumn = "portionId",
        entityColumn = "portionId"
    )
    val portion: DatabasePortion
)

fun List<QueryEntryWithData>.asDomainEntries() = map {
    Entry(
        id = it.entryId,
        product = it.product.asDomainProduct(),
        portion = it.portion.asDomainPortion(),
        portionCount = it.portionCount,
        timestamp = it.timestamp
    )
}

fun QueryProductWithData.asDomainProduct() =
    Product(
        id = productId,
        name = name,
        portions = portions.asDomainPortions(),
        nutrients = nutrients.asDomainNutrients(),
        nutrientAmounts = nutrients
            .map { it.asDomainNutrient() to nutrientJoins[nutrients.indexOf(it)].amount }
            .toMap()
    )

fun List<QueryProductWithData>.asDomainProducts() = map { it.asDomainProduct() }