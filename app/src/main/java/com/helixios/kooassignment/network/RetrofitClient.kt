package com.helixios.kooassignment.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    companion object{
        private const val BASEURL = "https://gorest.co.in/public/v1/"
        private var retrofit: Retrofit?=null
        fun getApiClient(): Retrofit  {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}