package com.mahmoudalyudeen.ambrosia.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Transaction
    @Query("select * from databaseproduct")
    fun getProducts(): LiveData<List<QueryProductWithData>>

    @Transaction
    @Query("select * from databaseproduct where productId = :productId limit 1")
    fun getProduct(productId: Int): LiveData<QueryProductWithData>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<DatabaseProduct>)

}

@Dao
interface PortionDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortions(portions: List<DatabasePortion>)

}

@Dao
interface NutrientDao {

    @Query("select * from databasenutrient order by category, nutrientId")
    fun getNutrients(): LiveData<List<DatabaseNutrient>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNutrients(nutrients: List<DatabaseNutrient>)

}

@Dao
interface ProductNutrientJoinDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(joins: List<ProductNutrientJoin>)

}

@Dao
interface EntryDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: DatabaseEntry)

    @Transaction
    @Query("select * from databaseentry")
    fun getEntries(): LiveData<List<QueryEntryWithData>>

}

@Database(
    version = 1,
    entities = [
        DatabaseProduct::class,
        DatabaseNutrient::class,
        DatabasePortion::class,
        DatabaseEntry::class,
        ProductNutrientJoin::class
    ]
)
abstract class FoodDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val nutrientDao: NutrientDao
    abstract val portionDao: PortionDao
    abstract val productNutrientJoinDao: ProductNutrientJoinDao
    abstract val entryDao: EntryDao
}

