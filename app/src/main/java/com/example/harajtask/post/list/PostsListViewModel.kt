package com.example.harajtask.post.list

import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PostsListViewModel(val assetManager: AssetManager): ViewModel() {


    fun parsePostsJson(){

        viewModelScope.launch(Dispatchers.IO) {

            val postsJsonString = loadJsonAsset()
            try {

                val moshi = Moshi.Builder().build()
                val listPostType = Types.newParameterizedType(List::class.java, PostModel::class.java)
                val jsonAdapter = moshi.adapter<List<PostModel>>(listPostType)

                val postsList = jsonAdapter.fromJson(postsJsonString)

            }

            catch (e: Exception){

                Log.d("gggg", "parsePostsJson: ${e.message}")
            }


        }
    }


    private fun loadJsonAsset(): String{

        return assetManager.open("data.json")
            .bufferedReader()
            .use { it.readText() }
    }


    class Factory(private val assetManager: AssetManager): ViewModelProvider.Factory{

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            return PostsListViewModel(assetManager) as T
        }
    }
}