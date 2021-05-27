package com.example.harajtask.post.list

import android.text.format.DateUtils
import java.time.format.DateTimeFormatter
import java.util.*

class PostModel(

    val title: String,
    unixDate: String,
    val poster: String,
    val location: String,
    val description: String,
    val imageUrl: String

    ){

    val timeSincePost: String

    init {
        timeSincePost = getTimeSincePost(unixDate.toLong() * 1000)
    }


    private fun getTimeSincePost(timeInMillis: Long): String{

        return DateUtils.getRelativeTimeSpanString(timeInMillis).toString()
    }
}
