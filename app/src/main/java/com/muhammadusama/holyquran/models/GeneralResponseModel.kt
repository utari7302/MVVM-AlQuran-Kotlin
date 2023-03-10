package com.muhammadusama.holyquran.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GeneralResponseModel {
    @SerializedName("isError")
    @Expose
    var isError: Boolean = false
    @SerializedName("messages")
    @Expose
    var messages: String? = null
}