package com.example.moviefind

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MovieSearch : AppCompatActivity() {
    lateinit var adapter: MovieAdapter
    lateinit var movieSearch: Movie
    lateinit var editText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        val recyclerView:RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        var editText = findViewById<EditText>(R.id.userInput)
        var rBtn = findViewById<Button>(R.id.retrieve)
        var sBtn = findViewById<Button>(R.id.saveToDB)

        var MY_API_KEY = "6ad84578"

        var data: String = ""

        rBtn.setOnClickListener {
            // start the fetching of data in the background
            var movieArray = ArrayList<Movie>()
            runBlocking {
                withContext(Dispatchers.IO) {
                    // this will contain the whole of JSON
                    val stb = StringBuilder("")


                    var MOVIE = editText.text.toString()
                    var url_string = "https://www.omdbapi.com/?t=$MOVIE&apikey=$MY_API_KEY"

                    val url = URL(url_string)
                    val con = url.openConnection() as HttpURLConnection
                    val bf: BufferedReader
                    try {
                        bf = BufferedReader(InputStreamReader(con.inputStream))
                    } catch (e: IOException) {
                        e.printStackTrace()
                        return@withContext
                    }
                    var line = bf.readLine()
                    while (line != null) {
                        stb.append(line)


                        var jsonObject = JSONObject(line)

                        movieSearch = Movie(
                            jsonObject.getString("Title") + "",
                            jsonObject.getString("Year") + "",
                            jsonObject.getString("Rated") + "",
                            jsonObject.getString("Released") + "",
                            jsonObject.getString("Runtime") + "",
                            jsonObject.getString("Genre") + "",
                            jsonObject.getString("Director") + "",
                            jsonObject.getString("Writer") + "",
                            jsonObject.getString("Actors") + "",
                            jsonObject.getString("Plot") + ""
                        )

                        var movieSearchChanged = Movie(
                            "Title : "+jsonObject.getString("Title") + "",
                            "Year : "+jsonObject.getString("Year") + "",
                            "Rated : "+jsonObject.getString("Rated") + "",
                            "Released : "+jsonObject.getString("Released") + "",
                            "Runtime : "+jsonObject.getString("Runtime") + "",
                            "Genre : "+jsonObject.getString("Genre") + "",
                            "Director : "+jsonObject.getString("Director") + "",
                            "Writer : "+jsonObject.getString("Writer") + "",
                            "Actor : "+jsonObject.getString("Actors") + "",
                            "Plot : "+jsonObject.getString("Plot") + ""
                        )

                        movieArray.add(movieSearchChanged)

//                        var output:String = "Title : "+jsonObject.getString("Title") +
//                                "\n"+"Year : "+jsonObject.getString("Year") +
//                                "\n"+"Rated : "+ jsonObject.getString("Rated") +
//                                "\n"+"Released : "+jsonObject.getString("Released") +
//                                "\n"+"Runtime : "+jsonObject.getString("Runtime") +
//                                "\n"+"Genre : "+jsonObject.getString("Genre") +
//                                "\n"+"Director : "+jsonObject.getString("Director") +
//                                "\n"+"Writer : "+jsonObject.getString("Writer") +
//                                "\n"+"Actor : "+jsonObject.getString("Actors") +
//                                "\n"+"Plot : "+jsonObject.getString("Plot")

                        line = bf.readLine()
                    }
                }
            }
            Toast.makeText(applicationContext, "Movie retrieved", Toast.LENGTH_SHORT).show()
            recyclerView.layoutManager= LinearLayoutManager(this.applicationContext)
            adapter = MovieAdapter(movieArray)
            recyclerView.adapter=adapter
            adapter.setOnItemClickListner(object :MovieAdapter.onItemClickListner{
                override fun onItemClick(position: Int) {
                }
            })

        }

        sBtn.setOnClickListener {
             var db = Room.databaseBuilder(
                this, MovieDatabase::class.java,
                "movie_find_database"
            ).build()
            runBlocking {
                launch {
                    val movieDao = db?.movieDao()
                    movieDao!!.insertMovies(movieSearch)

                    var movieAll = movieDao?.getAll()
                    print(movieAll)
                }
            }
            Toast.makeText(applicationContext, "Movie saved to database", Toast.LENGTH_SHORT).show()
        }

    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        mEditText.setText(SAVED_TEXT_KEY)
//        val myString = savedInstanceState.getString(SAVED_TEXT_KEY)
//    }

}