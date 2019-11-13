package com.mahmoudalyudeen.ambrosia.ui.portion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.mahmoudalyudeen.ambrosia.db.DatabaseEntry
import com.mahmoudalyudeen.ambrosia.domain.*
import com.mahmoudalyudeen.ambrosia.repo.EntryRepository
import com.mahmoudalyudeen.ambrosia.repo.ProductRepository
import kotlinx.coroutines.*

class PortionViewModel(
    private val entryRepository: EntryRepository,
    productRepository: ProductRepository,
    productId: Int
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _product = productRepository.getProduct(productId)
    val product: LiveData<Product?>
        get() = _product

    private val _selectedPortion: MutableLiveData<Portion?> =
        map(_product) { it?.portions?.firstOrNull() } as MutableLiveData<Portion?>
    val selectedPortion: LiveData<Portion?>
        get() = _selectedPortion

    private val _portionCount = MutableLiveData(1.0)
    val portionCount: LiveData<Double>?
        get() = _portionCount

    private val _eventNavigateBack = MutableLiveData<Boolean>()
    val eventNavigateBack: LiveData<Boolean>
        get() = _eventNavigateBack

    private val _nutrientsProgress = MediatorLiveData<List<NutrientAmount>>()
    val nutrientsProgress: LiveData<List<NutrientAmount>>
        get() = _nutrientsProgress

    private val _nutrientsProgressDetails = MutableLiveData<List<NutrientProgress>>()
    val nutrientsProgressDetails: LiveData<List<NutrientProgress>>
        get() = _nutrientsProgressDetails

    init {
        _nutrientsProgress.addSource(_product) {
            calculateNutrientsProgress(it, _selectedPortion.value, _portionCount.value)
        }
        _nutrientsProgress.addSource(_portionCount) {
            calculateNutrientsProgress(_product.value, _selectedPortion.value, it)
        }
        _nutrientsProgress.addSource(_selectedPortion) {
            calculateNutrientsProgress(_product.value, it, _portionCount.value)
        }
    }

    private fun calculateNutrientsProgress(product: Product?, portion: Portion?, portionCount: Double?) {
        if (product == null || portion == null || portionCount == null) return
        val nutrients = product.nutrients
        val entries = listOf(
            Entry(
                id = 0,
                product = product,
                portion = selectedPortion.value!!,
                portionCount = portionCount,
                timestamp = System.currentTimeMillis()
            )
        )
        _nutrientsProgressDetails.value = nutrients.asNutrientsProgress(entries)
    }

    fun onConfirmClick() {
        addEntry()
        _eventNavigateBack.value = true
    }

    private fun addEntry() {
        viewModelScope.launch {
            entryRepository.insertEntry(
                DatabaseEntry(
                    productId = _product.value!!.id,
                    portionId = _selectedPortion.value!!.id,
                    portionCount = _portionCount.value!!,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    fun onNavigateBackDone() {
        _eventNavigateBack.value = null
    }

    fun onPortionSelected(portionId: Int) {
        _selectedPortion.value = product.value?.portions?.firstOrNull { it.id == portionId }
    }

    fun onPortionCountChanged(newCountInput: Double?) {
        if (newCountInput != _portionCount.value) {
            _portionCount.value = newCountInput
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
