package com.muhammadusama.holyquran.repository

import androidx.lifecycle.MutableLiveData
import com.muhammadusama.holyquran.models.Data
import com.muhammadusama.holyquran.models.SurahList
import com.muhammadusama.holyquran.retrofit.QuranApi
import javax.inject.Inject

interface QuranRepository {

    // List of functions that communicate with different endpoints
    suspend fun getQuranSurah():MutableLiveData<SurahList> {
        return null!!
    }

    suspend fun getQuranSurahById():List<Data>{
        return emptyList()
    }


}