package com.mahmoudalyudeen.ambrosia.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import com.mahmoudalyudeen.ambrosia.db.FoodDatabase
import com.mahmoudalyudeen.ambrosia.db.asDomainNutrients
import com.mahmoudalyudeen.ambrosia.domain.Nutrient

class NutrientRepository(foodDatabase: FoodDatabase) {

    val nutrients: LiveData<List<Nutrient>> = map(foodDatabase.nutrientDao.getNutrients()) { it.asDomainNutrients() }

}