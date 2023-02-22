package com.muhammadusama.holyquran.retrofit

import com.muhammadusama.holyquran.models.Data
import com.muhammadusama.holyquran.models.SurahList
import com.muhammadusama.holyquran.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface QuranApi {

    // Get Quran Surah
    @GET(Constants.GET_SURAH)
    suspend fun getSurah(): SurahList
}