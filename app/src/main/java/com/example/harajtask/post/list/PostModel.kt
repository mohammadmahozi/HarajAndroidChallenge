package com.example.harajtask.post.list

import android.text.format.DateUtils
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostModel(

    val title: String,

    @Json(name = "date")
    val unixTimestamp: String,

    val commentCount: String,

    @Json(name = "username")
    val poster: String,

    val city: String,

    @Json(name = "body")
    val description: String,

    val thumbURL: String

    ){

    val timeSincePost: String

    init {
        timeSincePost = getTimeSincePost(unixTimestamp.toLong() * 1000)
    }


    private fun getTimeSincePost(timeInMillis: Long): String{

        return DateUtils.getRelativeTimeSpanString(timeInMillis).toString()
    }
}
