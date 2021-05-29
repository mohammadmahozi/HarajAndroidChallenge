package com.example.harajtask.post.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.harajtask.R

class PostAdapter(
    private val onItemClicked: (PostModel) -> Unit): RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    var postsList = listOf<PostModel>()


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val postImageView: ImageView = itemView.findViewById(R.id.post_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.post_title)
        private val timeTextView: TextView = itemView.findViewById(R.id.post_time)
        private val posterTextView: TextView = itemView.findViewById(R.id.poster)
        private val locationTextView: TextView = itemView.findViewById(R.id.post_location)
        private val commentCountTextView: TextView  = itemView.findViewById(R.id.comment_count)


        init {
            itemView.setOnClickListener {
                onItemClicked(postsList[absoluteAdapterPosition])
            }
        }

        fun bind(post: PostModel) {

            Glide
                .with(postImageView.context)
                .load(post.thumbURL)
                .transform(CenterCrop(), RoundedCorners(50))
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(postImageView)

            titleTextView.text = post.title
            timeTextView.text = post.timeSincePost
            posterTextView.text = post.poster
            locationTextView.text = post.city
            commentCountTextView.text = formatCommentCount(post.commentCount)

        }

        private fun formatCommentCount(commentCount: String): String{

            if (commentCount == "0"){
                //remove comment icon
                commentCountTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                return ""
            }

            return "($commentCount)"
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



















