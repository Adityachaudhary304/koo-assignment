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
    //private var dataSource:PostDataSource?=null
    var postModelListLiveData : MutableLiveData<JsonReturnModel>?=null
    var pageNo:Int=1

    init {
        //dataSource = PostDataSource()
        postModelListLiveData = MutableLiveData()
    }

    /*fun fetchPostData(){
        if(fetchPosts(pageNo)) {
            pageNo += 1
            Log.i("viewModel", "fetchPostData: $pageNo page")
        }
    }*/
    fun fetchPosts(): Boolean {
        val data = MutableLiveData<JsonReturnModel>()
        val apiInterface = RetrofitClient.getApiClient().create(GoRestApi::class.java)
        var success = false
        Log.e("dataSource view", "fetchPosts: called $pageNo" )

        if (pageNo != null) {
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
                    if (response.code() == 200 &&  res!=null){
                        data.value = res
                        postModelListLiveData!!.value = res
                        success = true
                        pageNo += 1
                        Log.d("dataSource", "API data: $success onResponse: $pageNo"+
                                res.toString())
                    }else{
                        data.value = null
                    }

                }
            })
        }
        Log.d("dataSource", "onResponse: $success api")
        return success
    }
}