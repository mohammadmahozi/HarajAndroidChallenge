package com.example.harajtask.post.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harajtask.R
import com.example.harajtask.Result

class PostsListFragment: Fragment() {


    private val postAdapter: PostAdapter by lazy { PostAdapter() }

    private val postsListViewModel: PostsListViewModel by lazy {
        ViewModelProvider(
            this,
            PostsListViewModel.Factory(
                requireContext().assets
            )

        ).get(PostsListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        postsListViewModel.postsLiveDataList.observe(viewLifecycleOwner, Observer {

            when(it){

                is Result.Success -> updateAdapterData(it.data)
                is Result.Error -> Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_LONG).show()
            }

        })

        return inflater.inflate(R.layout.fragment_posts_list, container, false)
    }

    private fun updateAdapterData(data: List<PostModel>){

        postAdapter .apply {

            postsList = data
            notifyDataSetChanged()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val postsRecyclerView = view.findViewById<RecyclerView>(R.id.posts_recycler_view).apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }
}