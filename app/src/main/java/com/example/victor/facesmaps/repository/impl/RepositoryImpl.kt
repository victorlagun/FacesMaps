package com.example.victor.facesmaps.repository.impl

import androidx.lifecycle.LiveData
import com.example.victor.facesmaps.impl.App
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.remote.Remote
import com.example.victor.facesmaps.remote.impl.RemoteImpl
import com.example.victor.facesmaps.repository.Repository
import io.reactivex.schedulers.Schedulers

object RepositoryImpl : Repository {
    private val database = App.instance.database
    private val remote = RemoteImpl(Remote.create())

    override fun getAll(page: Int): LiveData<List<User>?> {
        with(remote) {
            users(page)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ database.userDao().insert(it.data) },
                { it.printStackTrace() })
        }
        return database.userDao().getAll()
    }

    override fun getUser(id: Int): LiveData<User?> {
        return database.userDao().getById(id)
    }
}