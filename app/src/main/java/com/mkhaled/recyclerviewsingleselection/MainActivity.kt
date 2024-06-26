package com.mkhaled.recyclerviewsingleselection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val list = listOf(
            MyItem("all"),
            MyItem("restaurants"),
            MyItem("coffees"),
            MyItem("gyms")
        )
        rv.adapter = MyAdapter(list)
    }
}