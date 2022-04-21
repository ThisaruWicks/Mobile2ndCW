package com.example.moviefind

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version=1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao



}