@file:Suppress("AndroidUnresolvedRoomSqlReference")

package com.example.moviefind

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("Select * from movie")
    suspend fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(vararg movie: Movie)

    @Insert
    suspend fun insertAll(vararg movies: Movie)

    @Query("SELECT * FROM movie WHERE actors = :aName")
    fun searchActors(aName:String?): List<Movie>

}