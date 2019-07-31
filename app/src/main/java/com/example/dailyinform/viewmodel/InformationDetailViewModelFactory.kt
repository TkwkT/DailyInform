package com.example.dailyinform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailyinform.repository.DetailRepository

class InformationDetailViewModelFactory(
    private val repository: DetailRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InformationDetailViewModel(repository) as T
    }
}