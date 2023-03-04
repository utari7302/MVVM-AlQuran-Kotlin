package com.muhammadusama.holyquran.repository

import androidx.lifecycle.MutableLiveData
import androidx.room.withTransaction
import com.muhammadusama.holyquran.QuranDatabase
import com.muhammadusama.holyquran.models.Data
import com.muhammadusama.holyquran.models.SurahList
import com.muhammadusama.holyquran.retrofit.IGenericCallBack
import com.muhammadusama.holyquran.retrofit.QuranApi
import com.muhammadusama.holyquran.retrofit.SingleEnqueueCall
import com.muhammadusama.holyquran.utils.Constants
import javax.inject.Inject

class SurahRepositoryImplementation @Inject constructor(private val quranApi: QuranApi,private val quranDatabase: QuranDatabase) :QuranRepository,IGenericCallBack {

    var failureMessage: MutableLiveData<String> = MutableLiveData()
    var objResponse: MutableLiveData<List<Data>> = MutableLiveData()
    var quranDatabaseReference = quranDatabase.surahDao()

    override suspend fun getQuranSurah() {
        val responseOfQuranSurahFromDB = quranDatabaseReference.getQuranSurahList()
        if(responseOfQuranSurahFromDB.isNotEmpty()){
            objResponse.postValue(responseOfQuranSurahFromDB)
        }else{
            val call = quranApi.getSurah()
            SingleEnqueueCall.callRetrofit(call,Constants.GET_SURAH,false,this)
        }

    }

    override suspend fun success(apiName: String, response: Any?) {
        when(apiName){
            Constants.GET_SURAH -> {
                val responseModel = response as SurahList
                objResponse.postValue(responseModel.data)
                quranDatabase.withTransaction {
                    quranDatabaseReference.deleteQuranSurah()
                    quranDatabaseReference.addQuranSurahListInDB(responseModel.data)
                }
            }
        }
    }

    override suspend fun failure(apiName: String, message: String?) {
        when(apiName){
            Constants.GET_SURAH -> {
                failureMessage.setValue(message!!)
            }
        }
    }
}