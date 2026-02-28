package com.yusuf.personaltrainer.ui.viewModel

import androidx.lifecycle.ViewModel
import com.yusuf.personaltrainer.data.local.entity.MealEntryEntity
import com.yusuf.personaltrainer.data.repository.MealRepository
import com.yusuf.personaltrainer.utils.DateUtils

class MealViewModel(
    private val repository: MealRepository
) : ViewModel() {

    private val today = DateUtils.today()

    val todayEntries = repository.getEntriesByDate(today)

    fun calculateMealTotal(
        entries: List<MealEntryEntity>,
        mealType: String
    ): Double {
        return entries
            .filter { it.mealType == mealType }
            .sumOf { it.calories }
    }

    fun calculateDailyTotal(entries: List<MealEntryEntity>): Double {
        return entries.sumOf { it.calories }
    }
}