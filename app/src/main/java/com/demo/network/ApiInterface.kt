package com.ontracpt.network

import com.demo.model.ResponseDTO
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("dummy/")
    fun getCatSubCat(): Call<ResponseDTO>

}