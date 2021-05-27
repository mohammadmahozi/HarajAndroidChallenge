package com.example.harajtask

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.harajtask.post.list.PostsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val postsListFragment = PostsListFragment()
        supportFragmentManager.beginTransaction().add(R.id.container, postsListFragment).commit()

        val x = DateUtils.getRelativeTimeSpanString(1621848879)


        Log.d("gggg", "addition_isCorrect: $x")

    }
}