package com.muhammadusama.holyquran.repository

import androidx.lifecycle.MutableLiveData
import com.muhammadusama.holyquran.models.SurahList
import com.muhammadusama.holyquran.retrofit.IGenericCallBack
import com.muhammadusama.holyquran.retrofit.QuranApi
import com.muhammadusama.holyquran.retrofit.SingleEnqueueCall
import com.muhammadusama.holyquran.utils.Constants
import javax.inject.Inject

class SurahRepositoryImplementation @Inject constructor(private val quranApi: QuranApi) :QuranRepository,IGenericCallBack {

    var failureMessage: MutableLiveData<String> = MutableLiveData()
    var objResponse: MutableLiveData<SurahList> = MutableLiveData()

    override suspend fun getQuranSurah() {
        val call = quranApi.getSurah()
        SingleEnqueueCall.callRetrofit(call,Constants.GET_SURAH,false,this)
    }

    override fun success(apiName: String, response: Any?) {
        when(apiName){
            Constants.GET_SURAH -> {
                val responseModel = response as SurahList
                objResponse.postValue(responseModel)
            }
        }
    }

    override fun failure(apiName: String, message: String?) {
        when(apiName){
            Constants.GET_SURAH -> {
                failureMessage.setValue(message!!)
            }
        }
    }
}