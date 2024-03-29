package com.example.victor.facesmaps.repository.impl

import androidx.lifecycle.LiveData
import com.example.victor.facesmaps.App
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.remote.Remote
import com.example.victor.facesmaps.remote.impl.RemoteImpl
import com.example.victor.facesmaps.repository.Repository
import com.example.victor.facesmaps.util.ErrorHandler
import io.reactivex.schedulers.Schedulers

object RepositoryImpl : Repository {
    private val database = App.instance.database
    private val remote = RemoteImpl(Remote.create())
    private var pageCounter = 1
    private var totalPages = 2

    override fun getAll() {
        with(remote) {
            if (pageCounter <= totalPages)
            users(pageCounter)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ page -> page.data.forEach {
                        it.page = page.page
                        it.total_pages = page.total_pages
                    }
                    database.userDao().insert(page.data)
                    pageCounter++
                },
                    { ErrorHandler().handleError(it) })
        }
    }

    override fun getUser(id: Int): LiveData<User?> {
        return database.userDao().getById(id)
    }

    fun setDefault() {
        pageCounter = 1
        totalPages = 2
    }
}