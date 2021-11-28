package com.helixios.kooassignment.network

import com.helixios.kooassignment.data.JsonReturnModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoRestApi {
    @GET("posts")
    fun fetchPosts(@Query("page") page:Int): Call<JsonReturnModel>
}