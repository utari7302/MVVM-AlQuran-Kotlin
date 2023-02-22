package com.muhammadusama.holyquran.viewmodels

import androidx.lifecycle.ViewModel
import com.muhammadusama.holyquran.models.Data
import com.muhammadusama.holyquran.models.SurahList
import com.muhammadusama.holyquran.repository.SurahRepositoryImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor (private val surahRepositoryImplementation: SurahRepositoryImplementation):
    ViewModel() {

    suspend fun getSurahFromRepository():SurahList{
        return surahRepositoryImplementation.getQuranSurah()
    }
}