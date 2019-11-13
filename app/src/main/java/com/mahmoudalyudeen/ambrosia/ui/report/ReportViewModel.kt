package com.mahmoudalyudeen.ambrosia.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudalyudeen.ambrosia.domain.NutrientProgress
import com.mahmoudalyudeen.ambrosia.domain.asNutrientsProgress
import com.mahmoudalyudeen.ambrosia.repo.EntryRepository
import com.mahmoudalyudeen.ambrosia.repo.NutrientRepository

class ReportViewModel(nutrientRepository: NutrientRepository, entryRepository: EntryRepository) : ViewModel() {

    private val _entries = entryRepository.entries

    private val _nutrients = nutrientRepository.nutrients

    private val _nutrientsProgress = MediatorLiveData<List<NutrientProgress>>()
    val nutrientsProgress: LiveData<List<NutrientProgress>>
        get() = _nutrientsProgress

    private val _nutrientsProgressDetails = MutableLiveData<List<NutrientProgress>>()
    val nutrientsProgressDetails: LiveData<List<NutrientProgress>>
        get() = _nutrientsProgressDetails

    init {
        _nutrientsProgress.addSource(_nutrients) {
            _nutrientsProgressDetails.value = it.asNutrientsProgress(_entries.value)
        }
    }
}
