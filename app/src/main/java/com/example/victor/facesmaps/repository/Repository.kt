package com.example.victor.facesmaps.repository

import androidx.lifecycle.LiveData
import com.example.victor.facesmaps.model.User

interface Repository {
    fun getAll(page: Int): LiveData<List<User>?>

    fun getUser(id: Int): LiveData<User?>
}