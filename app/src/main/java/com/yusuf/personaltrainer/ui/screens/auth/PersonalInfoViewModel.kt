package com.yusuf.personaltrainer.ui.screens.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.local.dao.UserProfileDao
import com.yusuf.personaltrainer.data.local.entity.UserProfileEntity
import kotlinx.coroutines.launch

class PersonalInfoViewModel(
    application: Application
) : AndroidViewModel(application) {

    // ✅ DAO initialized safely (NO CRASH)
    private val dao: UserProfileDao =
        AppDatabase.getInstance(application).userProfileDao()

    fun saveProfile(
        name: String,
        age: String,
        weight: String,
        inch: String,
        phoneNumber: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        // ---------- VALIDATION ----------
        if (name.isBlank()) {
            onError("Name cannot be empty")
            return
        }

        val ageInt = age.toIntOrNull()
        val weightFloat = weight.toFloatOrNull()
        val inchInt = inch.toIntOrNull()

        if (ageInt == null || ageInt <= 0) {
            onError("Enter valid age")
            return
        }

        if (weightFloat == null || weightFloat <= 0f) {
            onError("Enter valid weight")
            return
        }

        if (inchInt == null || inchInt <= 0) {
            onError("Enter valid height")
            return
        }

        // ---------- DB OPERATION ----------
        viewModelScope.launch {
            try {
                dao.saveProfile(
                    UserProfileEntity(
                        name = name.trim(),
                        age = ageInt,
                        weightKg = weightFloat,
                        heightInch = inchInt,
                        phoneNumber = phoneNumber
                    )
                )
                onSuccess()
            } catch (e: Exception) {
                onError("Failed to save profile")
            }
        }
    }

    fun savePhoneNumber(
        phoneNumber: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {

            val existing = dao.getProfileOnce()

            if (existing == null) {
                // First time login → create row
                dao.saveProfile(
                    UserProfileEntity(
                        phoneNumber = phoneNumber
                    )
                )
            } else {
                // Row exists → update phone
                dao.saveProfile(
                    existing.copy(phoneNumber = phoneNumber)
                )
            }

            onSuccess()
        }
    }


    fun savePersonalInfo(
        name: String,
        age: String,
        weight: String,
        feet: String,
        inch: String,
        onSuccess: () -> Unit
    ) {
        val heightInInches =
            (feet.toIntOrNull() ?: 0) * 12 + (inch.toIntOrNull() ?: 0)

        viewModelScope.launch {

            val existing = dao.getProfileOnce()

            val updatedProfile = if (existing == null) {
                UserProfileEntity(
                    name = name,
                    age = age.toInt(),
                    weightKg = weight.toFloat(),
                    heightInch = heightInInches
                )
            } else {
                existing.copy(
                    name = name,
                    age = age.toInt(),
                    weightKg = weight.toFloat(),
                    heightInch = heightInInches
                )
            }

            dao.saveProfile(updatedProfile)
            onSuccess()
        }
    }

}
