package com.helixios.kooassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.helixios.kooassignment.data.JsonReturnModel
import com.helixios.kooassignment.network.GoRestApi
import com.helixios.kooassignment.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {
    var postModelListLiveData : MutableLiveData<JsonReturnModel>?=null
    var pageNo:Int=1

    init {
        postModelListLiveData = MutableLiveData()
    }

    fun fetchPosts(): Boolean {
        val data = MutableLiveData<JsonReturnModel?>()
        val apiInterface = RetrofitClient.getApiClient().create(GoRestApi::class.java)
        var success = false
        Log.e("dataSource view", "fetchPosts: called $pageNo" )

        apiInterface.fetchPosts(pageNo).enqueue(object : Callback<JsonReturnModel> {

            override fun onFailure(call: Call<JsonReturnModel>, t: Throwable) {
                data.value = null
                Log.e("dataSource", "onFailure: $call" )
            }

            override fun onResponse(
                call: Call<JsonReturnModel>,
                response: Response<JsonReturnModel>
            ) {
                Log.d("dataSource", "onResponse:API-- ${call.request()}")
                val res = response.body()
                if (response.code() == 200 &&  res!=null ){
                    data.value = res
                    postModelListLiveData!!.value = res!!
                    success = true
                    pageNo += 1
                    Log.d("dataSource", "API data: $success onResponse: $pageNo"+
                            res.toString())
                }else{
                    data.value = null
                }

            }
        })
        Log.d("dataSource", "onResponse: $success api")
        return success
    }
}