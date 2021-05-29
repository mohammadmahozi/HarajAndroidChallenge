package com.example.harajtask.post.list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harajtask.R
import com.example.harajtask.Result
import com.example.harajtask.post.PostModel
import com.example.harajtask.post.PostViewModel
import com.example.harajtask.post.details.PostDetailsFragment

class PostsListFragment: Fragment() {


    private val postAdapter: PostAdapter by lazy { PostAdapter(this::onPostClicked) }

    private val postViewModel: PostViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            PostViewModel.Factory(
                requireContext().assets
            )

        ).get(PostViewModel::class.java)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setHasOptionsMenu(true)

        postViewModel.postsLiveDataList.observe(viewLifecycleOwner, Observer {

            when (it) {

                is Result.Success -> updateAdapterData(it.data)
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        R.string.error_loading,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        })

        return inflater.inflate(R.layout.fragment_posts_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<RecyclerView>(R.id.posts_recycler_view).apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.post_list_fragment_menu, menu)

        val searchView = menu.findItem(R.id.product_search).actionView as SearchView
        initiateSearchView(searchView)

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initiateSearchView(searchView: SearchView){

        // Associate searchable configuration with the SearchView
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.apply {

            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

            setOnQueryTextListener(buildSearchQueryTextListener())
        }

    }

    private fun buildSearchQueryTextListener(): SearchView.OnQueryTextListener{

        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                updateAdapterData(postViewModel.getFilteredPosts(newText))
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                return true
            }
        }
    }

    private fun updateAdapterData(data: List<PostModel>){

        postAdapter .apply {

            postsList = data
            notifyDataSetChanged()
        }
    }


    private fun onPostClicked(postModel: PostModel){

        postViewModel.selectedPost = postModel

        navigateToPostDetails()
    }

    private fun navigateToPostDetails() {

        val postDetailsFragment = PostDetailsFragment()

        fragmentManager!!
            .beginTransaction()
            .replace(R.id.container, postDetailsFragment, "PostDetailsFragment")
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }


}