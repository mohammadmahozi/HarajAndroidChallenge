package com.example.harajtask.post.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.harajtask.R

class PostAdapter: RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    val postsList = listOf<PostModel>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val postImageView: ImageView = itemView.findViewById(R.id.post_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.post_title)
        private val timeTextView: TextView = itemView.findViewById(R.id.post_time)
        private val posterTextView: TextView = itemView.findViewById(R.id.poster)
        private val locationTextView: TextView = itemView.findViewById(R.id.post_location)



        fun bind(post: PostModel) {

            titleTextView.text = post.title
            timeTextView.text = post.timeSincePost
            posterTextView.text = post.poster
            locationTextView.text = post.location

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val post = postsList[position]
        holder.bind(post)
    }

    override fun getItemCount() = postsList.size

}



















