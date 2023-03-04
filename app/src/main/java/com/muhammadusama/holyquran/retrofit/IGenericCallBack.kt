package com.muhammadusama.holyquran.retrofit

import android.net.Uri

interface IGenericCallBack {

    suspend fun success(apiName: String, response: Any?)

    suspend fun failure(apiName: String, message: String?)

}

interface ICallBackUri {
    fun imageUri(result: Uri?)
}

interface ICallBackUriMultiple {
    fun imageUri(result: ArrayList<Uri>)
}