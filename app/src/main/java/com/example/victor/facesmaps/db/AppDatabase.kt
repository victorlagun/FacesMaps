package com.example.victor.facesmaps.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.victor.facesmaps.db.dao.UserDao
import com.example.victor.facesmaps.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}