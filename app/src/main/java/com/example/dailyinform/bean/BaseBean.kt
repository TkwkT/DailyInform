package com.example.dailyinform.bean

import com.google.gson.annotations.SerializedName

data class BaseBean<T>(
    @SerializedName("error")
    var error: Boolean,
    @SerializedName("results")
    var results: List<T>
)