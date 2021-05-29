package com.example.harajtask.post

import android.content.res.AssetManager
import androidx.lifecycle.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import com.example.harajtask.Result

class PostViewModel(private val assetManager: AssetManager): ViewModel() {


    lateinit var selectedPost: PostModel

    val postsLiveDataList = liveData(context = Dispatchers.IO) {

        val postsListResult = getPostsList()
        emit(postsListResult)
    }


    fun getFilteredPosts(query: String): List<PostModel>{

        if (postsLiveDataList.value == null || postsLiveDataList.value is Result.Error){

            return listOf()
        }

        val postsList = (postsLiveDataList.value as Result.Success<List<PostModel>>).data

        return postsList.filter {

            it.title.contains(query)
        }
    }


    private fun getPostsList(): Result<List<PostModel>>{

        return try {

            val postsJsonString = loadJsonAsset()

            val postsList = parseJsonToPosts(postsJsonString)
            Result.Success(postsList!!)
        }

        catch (e: Exception){

            Result.Error(e)
        }
    }


    private fun loadJsonAsset(): String{

        return assetManager.open("data.json")
            .bufferedReader()
            .use { it.readText() }
    }

    private fun parseJsonToPosts(jsonString: String): List<PostModel>? {

            val moshi = Moshi.Builder().build()
            val listPostType = Types.newParameterizedType(List::class.java, PostModel::class.java)
            val jsonAdapter = moshi.adapter<List<PostModel>>(listPostType)

            return jsonAdapter.fromJson(jsonString)
    }


    class Factory(private val assetManager: AssetManager): ViewModelProvider.Factory{

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            return PostViewModel(assetManager) as T
        }
    }
}