package com.example.dailyinform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailyinform.repository.DetailActivityRepository

class DetailActivityViewModelFactory(private val repository: DetailActivityRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailActivityViewModel(repository) as T
    }
}