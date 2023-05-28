package com.yusril.pitjarustest.data.api

import com.yusril.pitjarustest.data.model.Login
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {

    @FormUrlEncoded
    @POST("index.php/login/loginTest")
    suspend fun login(
        @Field("username") email :String,
        @Field("password") passwd :String
    ) : Login

}