package com.example.moviefind

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
//data class Movie(
//    @PrimaryKey val id: Int,
//    @ColumnInfo(name = "Year") val year: String?,
//    @ColumnInfo(name = "Rated") val rated: String?,
//    @ColumnInfo(name = "Released") val released: String?,
//    @ColumnInfo(name = "Runtime") val runtime: String?,
//    @ColumnInfo(name = "Genre") val genre: String?,
//    @ColumnInfo(name = "Director") val director: String?,
//    @ColumnInfo(name = "Writer") val writer: String?,
//    @ColumnInfo(name = "Actors") val actors: String?,
//    @ColumnInfo(name = "Plot") val plot: String?,
//
//)

//@Entity
//data class Movie(
//    @PrimaryKey(autoGenerate = true) val id: Long,
//    @ColumnInfo(name = "Year") val year: String?,
//    @ColumnInfo(name = "Rated") val rated: String?,
//    @ColumnInfo(name = "Released") val released: String?,
//    @ColumnInfo(name = "Runtime") val runtime: String?,
//    @ColumnInfo(name = "Genre") val genre: String?,
//    @ColumnInfo(name = "Director") val director: String?,
//    @ColumnInfo(name = "Writer") val writer: String?,
//    @ColumnInfo(name = "Actors") val actors: String?,
//    @ColumnInfo(name = "Plot") val plot: String?,
//){
//    constructor(year: String?,rated: String?,released: String?,
//    runtime: String?,genre: String?,director: String?,writer: String?,actors: String?,
//    plot: String?) : this(0, year, rated,released,runtime,genre,director,writer,actors,plot)
//}

@Entity
data class Movie(
    @PrimaryKey@ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Year") val year: String?,
    @ColumnInfo(name = "Rated") val rated: String?,
    @ColumnInfo(name = "Released") val released: String?,
    @ColumnInfo(name = "Runtime") val runtime: String?,
    @ColumnInfo(name = "Genre") val genre: String?,
    @ColumnInfo(name = "Director") val director: String?,
    @ColumnInfo(name = "Writer") val writer: String?,
    @ColumnInfo(name = "Actors") val actors: String?,
    @ColumnInfo(name = "Plot") val plot: String?,
)
