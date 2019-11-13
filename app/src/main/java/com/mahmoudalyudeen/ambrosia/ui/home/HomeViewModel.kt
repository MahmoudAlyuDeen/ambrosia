package com.mahmoudalyudeen.ambrosia.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.mahmoudalyudeen.ambrosia.domain.Entry
import com.mahmoudalyudeen.ambrosia.domain.NutrientProgress
import com.mahmoudalyudeen.ambrosia.domain.asNutrientsProgress
import com.mahmoudalyudeen.ambrosia.repo.EntryRepository
import com.mahmoudalyudeen.ambrosia.repo.NutrientRepository

class HomeViewModel(entryRepository: EntryRepository, nutrientRepository: NutrientRepository) : ViewModel() {

    private val _navigateAddEntry = MutableLiveData<Boolean>()
    val navigateAddEntry: LiveData<Boolean>
        get() = _navigateAddEntry

    private val _entries = entryRepository.entries
    val entries: LiveData<List<Entry>>
        get() = _entries

    private val _nutrients = map(nutrientRepository.nutrients) { list -> list.filter { it.glanceable } }

    private val _nutrientsProgress = MediatorLiveData<List<NutrientProgress>>()
    val nutrientsProgress: LiveData<List<NutrientProgress>>
        get() = _nutrientsProgress

    init {
        _nutrientsProgress.addSource(_entries) {
            _nutrientsProgress.value = _nutrients.value?.asNutrientsProgress(it)
        }
        _nutrientsProgress.addSource(_nutrients) {
            _nutrientsProgress.value = it.asNutrientsProgress(_entries.value)
        }
    }

    fun onAddEntryClick() {
        _navigateAddEntry.value = true
    }

    fun onNavigationAddEntryDone() {
        _navigateAddEntry.value = null
    }
}
