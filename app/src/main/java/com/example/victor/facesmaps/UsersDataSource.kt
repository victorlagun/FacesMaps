package com.example.victor.facesmaps

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PageKeyedDataSource
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.repository.impl.RepositoryImpl

class UsersDataSource(private val lifecycleOwner: LifecycleOwner) :
    PageKeyedDataSource<Int, User>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        val currentPage = 1
        val nextPage = currentPage + 1

        RepositoryImpl.getAll(currentPage).observe(
            lifecycleOwner, Observer {
                callback.onResult(it as MutableList<User>, null, nextPage)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val currentPage = params.key
        val nextPage = currentPage + 1

        RepositoryImpl.getAll(currentPage).observe(
            lifecycleOwner, Observer {
                callback.onResult(it as MutableList<User>, nextPage)
            })
    }
}