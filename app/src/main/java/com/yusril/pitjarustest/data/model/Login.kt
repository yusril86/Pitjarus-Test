package com.yusril.pitjarustest.data.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("stores")
    val stores  :List<Stores>,
    @SerializedName("message")
    val message :String,
    @SerializedName("status")
    val status :String,
)
