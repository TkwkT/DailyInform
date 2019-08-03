package com.example.dailyinform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailyinform.repository.BaseRepository
import com.example.dailyinform.repository.ClassifyRepository

class InformationClassifyViewModelFactory(private val repository: ClassifyRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InformationClassifyViewModel(repository) as T
    }
}