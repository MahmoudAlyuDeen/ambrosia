package com.mahmoudalyudeen.ambrosia.db

import androidx.room.*
import com.mahmoudalyudeen.ambrosia.domain.*

@Entity
data class DatabaseProduct(
    @PrimaryKey
    val productId: Int,
    val name: String
)

@Entity
data class DatabaseNutrient(
    @PrimaryKey
    val nutrientId: Int,
    val name: String,
    val unit: String,
    val target: Double,
    val category: String,
    val glanceable: Boolean
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = DatabaseProduct::class,
            parentColumns = ["productId"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )]
)
data class DatabasePortion(
    @PrimaryKey(autoGenerate = true)
    val portionId: Int = 0,
    val unit: String,
    val weight: Double,
    @ColumnInfo(index = true)
    val productId: Int
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = DatabaseProduct::class,
            parentColumns = ["productId"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DatabasePortion::class,
            parentColumns = ["portionId"],
            childColumns = ["portionId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )]
)
data class DatabaseEntry(
    @PrimaryKey(autoGenerate = true)
    val entryId: Int = 0,
    @ColumnInfo(index = true)
    val productId: Int,
    @ColumnInfo(index = true)
    val portionId: Int,
    val portionCount: Double,
    val timestamp: Long
)

@Entity(
    tableName = "product_nutrient_join",
    primaryKeys = ["productId", "nutrientId"],
    foreignKeys = [
        ForeignKey(
            entity = DatabaseProduct::class,
            parentColumns = ["productId"],
            childColumns = ["productId"]
        ),
        ForeignKey(
            entity = DatabaseNutrient::class,
            parentColumns = ["nutrientId"],
            childColumns = ["nutrientId"]
        )
    ]
)
data class ProductNutrientJoin(
    val productId: Int,
    @ColumnInfo(index = true)
    val nutrientId: Int,
    val amount: Double
)

fun DatabasePortion.asDomainPortion() = Portion(
    id = this.portionId,
    unit = this.unit,
    weight = this.weight
)

fun DatabaseNutrient.asDomainNutrient() = Nutrient(
    id = this.nutrientId,
    name = this.name,
    unit = this.unit,
    target = this.target,
    category = this.category,
    glanceable = this.glanceable
)

fun List<DatabasePortion>.asDomainPortions() = map {
    Portion(
        id = it.portionId,
        unit = it.unit,
        weight = it.weight
    )
}

fun List<DatabaseNutrient>.asDomainNutrients() = map { it.asDomainNutrient() }
