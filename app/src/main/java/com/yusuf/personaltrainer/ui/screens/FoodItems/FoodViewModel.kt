package com.yusuf.personaltrainer.ui.screens.FoodItems


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.personaltrainer.data.local.entity.FoodEntity
import com.yusuf.personaltrainer.data.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val repository: FoodRepository
) : ViewModel() {

    private val _foods = MutableStateFlow<List<FoodEntity>>(emptyList())
    val foods: StateFlow<List<FoodEntity>> = _foods

    fun loadFoods() {
        viewModelScope.launch {
            _foods.value = repository.getAllFoods()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _foods.value = repository.searchFoods(query)
        }
    }
}