package com.example.harajtask.post.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.post.PostViewModel

class PostDetailsFragment: Fragment() {

    private val postViewModel: PostViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            PostViewModel.Factory(
                requireContext().assets
            )

        ).get(PostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setHasOptionsMenu(true)


        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel.selectedPost.apply {

            Glide
                .with(requireContext())
                .load(thumbURL)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(view.findViewById<ImageView>(R.id.post_details_image))

            view.findViewById<TextView>(R.id.post_details_title).text = title
            view.findViewById<TextView>(R.id.post_details_date_time).text = formattedDateTime
            view.findViewById<TextView>(R.id.post_details_username).text = username
            view.findViewById<TextView>(R.id.city).text = city
            view.findViewById<TextView>(R.id.post_details_body).text = body

        }

    }
}