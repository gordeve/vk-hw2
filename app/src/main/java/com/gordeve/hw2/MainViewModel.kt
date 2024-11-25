package com.gordeve.hw2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gordeve.hw2.cataas.Cat
import com.gordeve.hw2.cataas.CataasService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _cats: MutableStateFlow<List<Cat>> = MutableStateFlow(emptyList())
    val cats = _cats.asStateFlow()

    val cataasService = CataasService()

    fun fetchCats() {
        viewModelScope.launch {
            _cats.update { cats ->
                _isLoading.value = true
                try {
                    val newCats = cataasService.getCats(skip = 0, limit = 50)
                    _error.value = null
                    cats + newCats
                } catch (e: Exception) {
                    _error.value = e
                    cats
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }
}