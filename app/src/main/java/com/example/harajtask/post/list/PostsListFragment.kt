package com.example.harajtask.post.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class PostsListFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val postsListViewModel = ViewModelProvider(
            this,
            PostsListViewModel.Factory(
                requireContext().assets
            )

        ).get(PostsListViewModel::class.java)

        postsListViewModel.parsePostsJson()
    }
}