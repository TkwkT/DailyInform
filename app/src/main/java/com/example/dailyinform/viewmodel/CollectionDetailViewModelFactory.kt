package com.example.dailyinform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailyinform.repository.CollectionDetailRepository

class CollectionDetailViewModelFactory(private val repository: CollectionDetailRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CollectionDetailViewModel(repository) as T
    }
}