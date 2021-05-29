package com.example.harajtask.post.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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


        Log.d("gggg", "onCreateView: ${postViewModel.selectedPost.title}")
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }
}