package com.example.victor.facesmaps.repository

import androidx.lifecycle.LiveData
import com.example.victor.facesmaps.model.User
import io.reactivex.Flowable

interface Repository {
    fun getAll(page: Int): Flowable<List<User>?>

    fun getUser(id: Int): LiveData<User?>
}