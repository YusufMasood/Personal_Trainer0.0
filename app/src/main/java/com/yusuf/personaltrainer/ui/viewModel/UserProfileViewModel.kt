package com.yusuf.personaltrainer.ui.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.local.dao.UserProfileDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class UserProfileViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dao: UserProfileDao =
        AppDatabase.getInstance(application).userProfileDao()

    val profile = dao.getProfile()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            null
        )
}
