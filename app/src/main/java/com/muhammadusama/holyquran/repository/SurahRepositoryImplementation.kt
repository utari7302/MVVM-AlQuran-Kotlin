package com.muhammadusama.holyquran.repository

import com.muhammadusama.holyquran.models.Data
import com.muhammadusama.holyquran.models.SurahList
import com.muhammadusama.holyquran.retrofit.QuranApi
import javax.inject.Inject

class SurahRepositoryImplementation @Inject constructor(private val quranApi: QuranApi) :QuranRepository {


    override suspend fun getQuranSurah(): SurahList {
        return quranApi.getSurah();
    }
}