package com.example.harajtask.post

import android.text.format.DateFormat
import android.text.format.DateUtils
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostModel(

    val title: String,

    @Json(name = "date")
    val unixTimestamp: String,

    val commentCount: String,

    val username: String,

    val city: String,

    val body: String,

    val thumbURL: String

    ){

    val timeSincePost: String

    val formattedDateTime: String

    init {
        //multiply to add milliseconds
        timeSincePost = getTimeSincePost(unixTimestamp.toLong() * 1000)
        formattedDateTime = getFormattedDateTime(unixTimestamp.toLong() * 1000)
    }


    private fun getTimeSincePost(timeInMillis: Long): String{

        return DateUtils.getRelativeTimeSpanString(timeInMillis).toString()
    }

    private fun getFormattedDateTime(timeInMillis: Long): String{

        return DateFormat.format("yyyy/MM/dd hh:mm aa", timeInMillis).toString()
    }
}
