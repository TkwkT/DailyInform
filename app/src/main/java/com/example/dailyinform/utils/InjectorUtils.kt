package com.example.dailyinform.utils

import android.content.Context
import com.example.dailyinform.database.AppDatabase
import com.example.dailyinform.repository.ClassifyRepository
import com.example.dailyinform.repository.DetailRepository
import com.example.dailyinform.viewmodel.InformationClassifyViewModel
import com.example.dailyinform.viewmodel.InformationClassifyViewModelFactory
import com.example.dailyinform.viewmodel.InformationDetailViewModel
import com.example.dailyinform.viewmodel.InformationDetailViewModelFactory

object InjectorUtils {

    fun getClassifyRepository(context: Context): ClassifyRepository {
        return ClassifyRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).classifyDao()
        )
    }

    fun provideInfromationClassifyViewModelFactory(context: Context): InformationClassifyViewModelFactory {
        val repository = getClassifyRepository(context)
        return InformationClassifyViewModelFactory(repository)
    }

    fun getDetailRepository(context: Context): DetailRepository {
        return DetailRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).detailDao()
        )
    }

    fun provideInformationDetailViewModelFactory(context: Context): InformationDetailViewModelFactory {
        val repository = getDetailRepository(context)
        return InformationDetailViewModelFactory(repository)
    }
}