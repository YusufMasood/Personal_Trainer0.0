package com.yusuf.personaltrainer.ui.viewModel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.local.entity.DailyNutritionEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class UserProfileViewModel(
    application: Application
) : AndroidViewModel(application) {





    private val _name = MutableStateFlow("Lucifer")
    val name: StateFlow<String> = _name

    private val _weight = MutableStateFlow(80)
    val weight: StateFlow<Int> = _weight

    private val _calories = MutableStateFlow(2100)
    val calories: StateFlow<Int> = _calories

    private val _weeklyProgress = MutableStateFlow(0.7f)
    val weeklyProgress: StateFlow<Float> = _weeklyProgress

    private val _selectedColor = MutableStateFlow(Color(0xFF00E5FF))
    val selectedColor: StateFlow<Color> = _selectedColor

    fun updateColor(color: Color) {
        _selectedColor.value = color
    }

    fun logout() {
        // TODO: Add Firebase logout here
    }











    // Database
    private val db = AppDatabase.getInstance(application)

    // DAOs
    private val userProfileDao = db.userProfileDao()
    private val dailyNutritionDao = db.dailyNutritionDao()

    // Today date (yyyy-MM-dd)
    private val today = LocalDate.now().toString()

    /* ---------------- USER PROFILE ---------------- */

    val profile = userProfileDao.getProfile()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    /* ---------------- DAILY NUTRITION ---------------- */

    val todayNutrition = dailyNutritionDao.getByDateFlow(today)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun addCalories(
        calories: Int,
        protein: Int,
        carbs: Int,
        fat: Int
    ) = viewModelScope.launch {

        val current = dailyNutritionDao.getByDate(today)

        val updated = if (current == null) {
            DailyNutritionEntity(
                date = today,
                calories = calories,
                protein = protein,
                carbs = carbs,
                fat = fat
            )
        } else {
            current.copy(
                calories = current.calories + calories,
                protein = current.protein + protein,
                carbs = current.carbs + carbs,
                fat = current.fat + fat
            )
        }

        dailyNutritionDao.upsert(updated)
    }
}
