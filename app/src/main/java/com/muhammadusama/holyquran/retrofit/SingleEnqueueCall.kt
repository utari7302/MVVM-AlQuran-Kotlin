package com.muhammadusama.holyquran.retrofit

import com.google.gson.GsonBuilder
import com.muhammadusama.holyquran.models.GeneralResponseModel
import com.muhammadusama.holyquran.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

object SingleEnqueueCall {
    var retryCount = 0

    fun <T> callRetrofit(
        call: Call<T>,
        apiName: String,
        isLoaderShown: Boolean,
        apiListener: IGenericCallBack
    ) {
        call.enqueue(object : Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.isSuccessful){
                    retryCount = 0
                    CoroutineScope(Dispatchers.IO).launch {
                        apiListener.success(apiName,response.body())
                    }

                }else{
                    when{
                        response.code() == 401 -> {
                            // Token Expired
                            return
                        }
                        response.errorBody() != null -> try {
                            retryCount =0
                            val gson = GsonBuilder().create()
                            try {
                                val errorModel: GeneralResponseModel= gson.fromJson(response.errorBody()!!.toString(), GeneralResponseModel::class.java)
                                CoroutineScope(Dispatchers.IO).launch {
                                    apiListener.failure(apiName,errorModel.messages)
                                }

                            }catch (ex: Exception){
                                ex.printStackTrace()
                                CoroutineScope(Dispatchers.IO).launch {
                                    apiListener.failure(apiName,Constants.CONST_SERVER_NOT_RESPONDING)
                                }

                            }

                        }catch (e: IOException){
                            e.printStackTrace()
                            CoroutineScope(Dispatchers.IO).launch {
                                apiListener.failure(apiName,Constants.CONST_SERVER_NOT_RESPONDING)
                            }

                        }
                        else -> {
                            CoroutineScope(Dispatchers.IO).launch {
                                apiListener.failure(apiName,Constants.CONST_SERVER_NOT_RESPONDING)
                            }

                            return
                        }
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val callBack = this
                if(t.message != "Canceled"){
                    if(t is UnknownHostException || t is IOException){
                        enqueueWithRetry(call, callBack, isLoaderShown)
                    }else{
                        retryCount = 0
                        CoroutineScope(Dispatchers.IO).launch {
                            apiListener.failure(apiName, t.toString())
                        }

                    }
                }else{
                    retryCount = 0
                }
            }

        })
    }

    fun <T> enqueueWithRetry(call: Call<T>, callback: Callback<T>, isLoaderShown: Boolean) {
        call.clone().enqueue(callback)
    }
}