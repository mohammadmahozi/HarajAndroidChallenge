package com.example.harajtask.post.list

import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.harajtask.Result

class PostsListViewModel(private val assetManager: AssetManager): ViewModel() {



    val postsLiveDataList = liveData {

        val postsListResult = getPostsList()
        emit(postsListResult)

    }

    private fun getPostsList(): Result<List<PostModel>>{

            val postsJsonString = loadJsonAsset()

            return parseJsonToPosts(postsJsonString)
    }


    private fun loadJsonAsset(): String{

        return assetManager.open("data.json")
            .bufferedReader()
            .use { it.readText() }
    }

    private fun parseJsonToPosts(jsonString: String): Result<List<PostModel>> {

        return try {

            val moshi = Moshi.Builder().build()
            val listPostType = Types.newParameterizedType(List::class.java, PostModel::class.java)
            val jsonAdapter = moshi.adapter<List<PostModel>>(listPostType)

            val postsList = jsonAdapter.fromJson(jsonString)

            Result.Success(postsList!!)

        }

        catch (e: Exception){

            Result.Error(e)
        }

    }


    class Factory(private val assetManager: AssetManager): ViewModelProvider.Factory{

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            return PostsListViewModel(assetManager) as T
        }
    }
}