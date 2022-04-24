package com.example.moviefind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchActors : AppCompatActivity() {
    lateinit var adapter1: MovieAdapter
    var movieArrayS = ArrayList<Movie>()
    lateinit var outputV: List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_actors)

        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView1)

        val searchActorsBtn = findViewById<Button>(R.id.searchActorsBtn)
        val actorName = findViewById<EditText>(R.id.userInputActors)

        searchActorsBtn.setOnClickListener {
            var db = Room.databaseBuilder(
                this, MovieDatabase::class.java,
                "movie_find_database"
            ).build()
            runBlocking {
                launch {
                    var movieDao1 = db?.movieDao()
                    outputV = movieDao1!!.searchActors(actorName.text.toString())
                    if (outputV.size == 0) {
                        Toast.makeText(applicationContext, "Not fount", Toast.LENGTH_SHORT).show()
                    } else {
                        movieDao1!!.searchActors(actorName.text.toString())
                        var i = 0
                        while (i <= outputV.size - 1) {
                            movieArrayS.add(outputV[i])
                            i++
                        }

                    }
                }
            }
            recyclerView.layoutManager = LinearLayoutManager(this.applicationContext)
            adapter1 = MovieAdapter(outputV)
            recyclerView.adapter = adapter1
            adapter1.setOnItemClickListner(object : MovieAdapter.onItemClickListner {
                override fun onItemClick(position: Int) {
                }
            })
        }

    }
}