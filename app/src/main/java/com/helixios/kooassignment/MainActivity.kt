package com.helixios.kooassignment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.AbsListViewBindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helixios.kooassignment.databinding.ActivityMainBinding
import com.helixios.kooassignment.ui.PostRecyclerAdapter
import com.helixios.kooassignment.viewmodel.PostViewModel
import com.helixios.kooassignment.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var mViewModel: PostViewModel
    private lateinit var mRecyclerAdapter: PostRecyclerAdapter
    private lateinit var binding: ActivityMainBinding
//    private val retrofit = RetrofitClient.getApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show()
        mViewModel = ViewModelProvider(this,ViewModelFactory()).get(PostViewModel::class.java)
        //val apiData:JsonReturnModel = viewModel.fetchPostData()
        //binding.recyclerView.adapter = adapter

        initRecyclerView()
        initViewModel()

        binding.postRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!binding.postRecyclerView.canScrollVertically(1)) {
                    mViewModel.fetchPosts()
                }
            }
        })
    }
    fun initRecyclerView()
    {
        binding.postRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerAdapter = PostRecyclerAdapter()
        binding.postRecyclerView.adapter = mRecyclerAdapter
    }
    fun initViewModel()
    {
        mViewModel.postModelListLiveData?.observe(this, {
            Log.d(TAG, "initViewModel: observed")
            /*if(mViewModel.postModelListLiveData!!.value!!.meta!!.pagination!!.page!! < mViewModel!!.postModelListLiveData!!.value!!.meta!!.pagination!!.pages!!)
            {
                mViewModel.pageNo += 1
                Log.d("viewModel", "fetchPostData: ${mViewModel.pageNo}")
            }*/
            if (it != null) {
                Log.d("viewmodel", "onCreate: $it")
                if(mViewModel.pageNo > 1) {
                    var c = mRecyclerAdapter.itemCount
                    mRecyclerAdapter.addDataItems(it)
                    mRecyclerAdapter.notifyItemRangeInserted(c ,it.data!!.size)
                }
                else {
                    mRecyclerAdapter.setDataItems( it )
                    mRecyclerAdapter.notifyDataSetChanged()
                }
            } else {
                Toast.makeText(this, "Bhakk", Toast.LENGTH_LONG).show()
            }
        })
        Log.d(TAG, "initViewModel: line50")
        //mViewModel.fetchPostData()
        mViewModel.fetchPosts()
    }
}