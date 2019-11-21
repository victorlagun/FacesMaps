package com.example.victor.facesmaps.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.victor.facesmaps.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): DataSource.Factory<Int, User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getById(id: Int): LiveData<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<User>)

    @Query("DELETE FROM user")
    fun delete()
}