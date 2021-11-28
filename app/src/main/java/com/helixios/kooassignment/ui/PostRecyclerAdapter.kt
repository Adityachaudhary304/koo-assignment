package com.helixios.kooassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helixios.kooassignment.R
import com.helixios.kooassignment.data.JsonReturnModel
import com.helixios.kooassignment.data.PostModel

class PostRecyclerAdapter : RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder>() {

    private var dataItems: MutableList<PostModel> ?=null

    fun setDataItems(dataItems:JsonReturnModel)
    {
        this.dataItems?.clear()
        this.dataItems = dataItems.data
    }

    fun addDataItems(dataItems:JsonReturnModel)
    {
        this.dataItems?.addAll(dataItems.data!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_tuple, parent,false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(dataItems!![position])
    }

    override fun getItemCount(): Int {
        if (dataItems==null)
            return 0
        else
            return dataItems?.size!!
    }

    class PostViewHolder constructor(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        private val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        private val postID: TextView = itemView.findViewById(R.id.postID)
        private val postBody: TextView = itemView.findViewById(R.id.postBody)

        fun bind(post: PostModel)
        {
            postTitle.text = post.title
            postID.text = post.id.toString()
            postBody.text = post.body
        }
    }


}