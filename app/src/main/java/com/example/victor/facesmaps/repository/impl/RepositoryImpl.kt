package com.example.victor.facesmaps.repository.impl

import androidx.lifecycle.LiveData
import com.example.victor.facesmaps.impl.App
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.remote.Remote
import com.example.victor.facesmaps.remote.impl.RemoteImpl
import com.example.victor.facesmaps.repository.Repository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

object RepositoryImpl : Repository {
    private val database = App.instance.database
    private val remote = RemoteImpl(Remote.create())

    override fun getAll(page: Int): Flowable<List<User>?> {
        with(remote) {
            users(page)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ page -> page.data.forEach {
                        it.page = page.page
                        it.total_pages = page.total_pages
                    }
                    database.userDao().insert(page.data)
                },
                    { it.printStackTrace() })
        }
        return database.userDao().getAll(page)
    }

    override fun getUser(id: Int): LiveData<User?> {
        return database.userDao().getById(id)
    }
}