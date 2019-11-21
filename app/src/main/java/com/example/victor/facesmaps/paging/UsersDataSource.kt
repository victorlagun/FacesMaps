package com.example.victor.facesmaps.paging

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.repository.impl.RepositoryImpl
import com.example.victor.facesmaps.util.ErrorHandler

class UsersDataSource :
    PageKeyedDataSource<Int, User>() {

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        val currentPage = 1
        val nextPage = currentPage + 1

        RepositoryImpl.getAll(currentPage).subscribe( { with(callback) { onResult(it as MutableList<User>, null, nextPage) } }, { ErrorHandler().handleError(it) })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val currentPage = params.key
        val nextPage = currentPage + 1

        RepositoryImpl.getAll(currentPage).subscribe( { with(callback) { onResult(it as MutableList<User>, nextPage) } }, { ErrorHandler().handleError(it) })
    }
}