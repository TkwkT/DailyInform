package com.example.dailyinform.utils

import android.content.Context
import com.example.dailyinform.database.AppDatabase
import com.example.dailyinform.repository.ClassifyRepository
import com.example.dailyinform.repository.CollectionDetailRepository
import com.example.dailyinform.repository.DetailActivityRepository
import com.example.dailyinform.repository.DetailRepository
import com.example.dailyinform.viewmodel.CollectionDetailViewModelFactory
import com.example.dailyinform.viewmodel.DetailActivityViewModelFactory
import com.example.dailyinform.viewmodel.InformationClassifyViewModelFactory
import com.example.dailyinform.viewmodel.InformationDetailViewModelFactory

object InjectorUtils {

    fun getClassifyRepository(context: Context): ClassifyRepository {
        return ClassifyRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).classifyDao()
        )
    }

    fun provideInformationClassifyViewModelFactory(context: Context): InformationClassifyViewModelFactory {
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

    fun getCollectionDetailRepository(context: Context): CollectionDetailRepository {
        return CollectionDetailRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).collectionDetailDao()
        )
    }

    fun provideCollectionDetailViewModelFactory(context: Context): CollectionDetailViewModelFactory {
        val repository = getCollectionDetailRepository(context)
        return CollectionDetailViewModelFactory(repository)
    }

    fun getDetailActivityRepository(context: Context): DetailActivityRepository {
        return DetailActivityRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).collectionDetailDao()
        )
    }

    fun provideDetailActivityViewModelFactory(context: Context): DetailActivityViewModelFactory {
        val repository = getDetailActivityRepository(context)
        return DetailActivityViewModelFactory(repository)
    }
}