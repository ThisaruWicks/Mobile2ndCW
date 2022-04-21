package com.example.moviefind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var db: MovieDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // create the database
        db = Room.databaseBuilder(
            this, MovieDatabase::class.java,
            "movie_find_database"
        ).build()

    }

    fun searchForActors(view: View) {
        val intent = Intent(this, SearchActors::class.java)
        startActivity(intent)
    }
    fun searchForMovies(view: View) {
        val intent = Intent(this, MovieSearch::class.java)
        startActivity(intent)
    }
    fun addMoviesToDb(view: View) {
        val json = """[ 
            {
            "Id" : "1",
            "Title":"The Shawshank Redemption",
            "Year":"1994",
            "Rated":"R",
            "Released":"14 Oct 1994",
            "Runtime":"142 min",
            "Genre":"Drama",
            "Director":"Frank Darabont",
            "Writer":"Stephen King, Frank Darabont",
            "Actors":"Tim Robbins, Morgan Freeman, Bob Gunton",
            "Plot":"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
            },
            {
            "Id" : "2",
            "Title":"Batman: The Dark Knight Returns, Part 1",
            "Year":"2012",
            "Rated":"PG-13",
            "Released":"25 Sep 2012",
            "Runtime":"76 min",
            "Genre":"Animation, Action, Crime, Drama, Thriller",
            "Director":"Jay Oliva",
            "Writer":"Bob Kane (character created by: Batman), Frank Miller (comic book), Klaus Janson (comic book), Bob Goodman",
            "Actors":"Peter Weller, Ariel Winter, David Selby, Wade Williams",
            "Plot":"Batman has not been seen for ten years. A new breed of criminal ravages Gotham City, forcing 55-year-old Bruce Wayne back into the cape and cowl. But, does he still have what it takes to fight crime in a new era?"
            },
            {
            "Id" : "3",
            "Title":"The Lord of the Rings: The Return of the King",
            "Year":"2003",
            "Rated":"PG-13",
            "Released":"17 Dec 2003",
            "Runtime":"201 min",
            "Genre":"Action, Adventure, Drama",
            "Director":"Peter Jackson",
            "Writer":"J.R.R. Tolkien, Fran Walsh, Philippa Boyens",
            "Actors":"Elijah Wood, Viggo Mortensen, Ian McKellen",
            "Plot":"Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."
            },
            {
            "Id" : "4",
            "Title":"Inception",
            "Year":"2010",
            "Rated":"PG-13",
            "Released":"16 Jul 2010",
            "Runtime":"148 min",
            "Genre":"Action, Adventure, Sci-Fi",
            "Director":"Christopher Nolan",
            "Writer":"Christopher Nolan",
            "Actors":"Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page",
            "Plot":"A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster."
            },
            {
            "Id" : "5",
            "Title":"The Matrix",
            "Year":"1999",
            "Rated":"R",
            "Released":"31 Mar 1999",
            "Runtime":"136 min",
            "Genre":"Action, Sci-Fi",
            "Director":"Lana Wachowski, Lilly Wachowski",
            "Writer":"Lilly Wachowski, Lana Wachowski",
            "Actors":"Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
            "Plot":"When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence."
            }
            ]"""

        runBlocking {
            launch {
                val movieDao = db?.movieDao()
                var jsonArray = JSONArray(json)
                for (jsonIndex in 0..(jsonArray.length() - 1)) {
                    val movie = Movie(
                        jsonArray.getJSONObject(jsonIndex).getString("Title") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Year") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Rated") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Released") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Runtime") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Genre") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Director") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Writer") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Actors") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Plot") + "",
                        jsonArray.getJSONObject(jsonIndex).getString("Id").toInt()
                    )
                    movieDao!!.insertMovies(movie)
                }
                var movieAll = movieDao?.getAll()
                print(movieAll)
            }
        }


    }


}
