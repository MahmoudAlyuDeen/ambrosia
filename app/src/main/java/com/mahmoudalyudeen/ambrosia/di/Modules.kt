package com.mahmoudalyudeen.ambrosia.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mahmoudalyudeen.ambrosia.db.FoodDatabase
import com.mahmoudalyudeen.ambrosia.repo.*
import com.mahmoudalyudeen.ambrosia.ui.home.HomeViewModel
import com.mahmoudalyudeen.ambrosia.ui.portion.PortionViewModel
import com.mahmoudalyudeen.ambrosia.ui.products.ProductsViewModel
import com.mahmoudalyudeen.ambrosia.ui.report.ReportViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/** Database dependency */
val dataModule = module {

    single {
        Room.databaseBuilder(get(), FoodDatabase::class.java, "fooddatabase")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    get<SeedRepository>().initializeData()
                }
            })
            .build()
    }

    single { SeedRepository(foodDatabase = get()) }

}

/** ViewModel and Repository dependencies for the home view */
val homeModule = module {

    single { EntryRepository(foodDatabase = get()) }
    single { NutrientRepository(foodDatabase = get()) }

    viewModel { HomeViewModel(entryRepository = get(), nutrientRepository = get()) }

}

/** ViewModel and Repository dependencies for the products view */
val productsModule = module {

    single { ProductRepository(foodDatabase = get()) }

    viewModel { ProductsViewModel(productRepository = get()) }

}

/** ViewModel dependency for the portions view */
val portionsModule = module {

    viewModel { (productId: Int) ->
        PortionViewModel(entryRepository = get(), productRepository = get(), productId = productId)
    }

}

/** ViewModel dependency for the report view */
val reportModule = module {

    viewModel { ReportViewModel(entryRepository = get(), nutrientRepository = get()) }

}

