package com.trekkarnataka.app.ui

import androidx.lifecycle.ViewModel
import com.trekkarnataka.app.data.Trek
import com.trekkarnataka.app.data.TrekRepository
import kotlinx.coroutines.flow.*

data class UiState(val query: String = "", val districts: List<String> = TrekRepository.districts)

class TrekViewModel : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onQuery(q: String) = _state.update {
        it.copy(query = q, districts = TrekRepository.districts.filter { d -> d.contains(q.trim(), ignoreCase = true) })
    }
    fun treks(district: String): List<Trek> = TrekRepository.treksIn(district)
    fun trek(id: String): Trek = TrekRepository.find(id)
}
