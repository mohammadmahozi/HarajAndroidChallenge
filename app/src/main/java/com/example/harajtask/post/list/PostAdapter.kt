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
import com.example.harajtask.post.PostModel

class PostAdapter(
    private val onItemClicked: (PostModel) -> Unit
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    var postsList = listOf<PostModel>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val postImageView: ImageView = itemView.findViewById(R.id.post_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.post_title)
        private val timeTextView: TextView = itemView.findViewById(R.id.post_date_time)
        private val posterTextView: TextView = itemView.findViewById(R.id.username)
        private val locationTextView: TextView = itemView.findViewById(R.id.post_location)
        private val commentCountTextView: TextView = itemView.findViewById(R.id.comment_count)


        init {
            itemView.setOnClickListener {
                onItemClicked(postsList[absoluteAdapterPosition])
            }
        }

        fun bind(post: PostModel) {

            post.apply {

                Glide
                    .with(postImageView.context)
                    .load(thumbURL)
                    .transform(CenterCrop(), RoundedCorners(50))
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .into(postImageView)

                titleTextView.text = title
                timeTextView.text = timeSincePost
                posterTextView.text = username
                locationTextView.text = city
                commentCountTextView.text = formatCommentCount(commentCount)
            }


        }

        private fun formatCommentCount(commentCount: String): String {

            if (commentCount == "0") {
                //remove comment icon
                commentCountTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                return ""
            }

            return "($commentCount)"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val post = postsList[position]
        holder.bind(post)
    }

    override fun getItemCount() = postsList.size

}



















