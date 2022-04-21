package com.example.moviefind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchActors : AppCompatActivity() {
    lateinit var adapter: MovieAdapter
    var movieArray = ArrayList<Movie>()

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
                    val movieDao = db?.movieDao()
                    movieDao!!.searchActors(actorName.text.toString())

                    var movieAll = movieDao?.getAll()
                    print(movieAll)
                }
            }
            Toast.makeText(applicationContext, "Searched", Toast.LENGTH_SHORT).show()
            recyclerView.layoutManager= LinearLayoutManager(this.applicationContext)
            adapter = MovieAdapter(movieArray)
            recyclerView.adapter=adapter
            adapter.setOnItemClickListner(object :MovieAdapter.onItemClickListner{
                override fun onItemClick(position: Int) {
                }
            })
        }

    }
}