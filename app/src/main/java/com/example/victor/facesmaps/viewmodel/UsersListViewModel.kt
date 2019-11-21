package com.example.victor.facesmaps.viewmodel

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.paging.UsersAdapter
import com.example.victor.facesmaps.paging.UsersDataSource

class UsersListViewModel : ViewModel() {

    private val adapter = UsersAdapter()
    private val dataSource = UsersDataSource()

    fun getAdapter(): UsersAdapter {
        adapter.submitList(getPagedList())
        return adapter
    }

    fun getDataSource(): UsersDataSource {
        return dataSource
    }

    private fun getPagedList(): PagedList<User> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .build()
        return PagedList.Builder<Int, User>(dataSource, config)
            .setFetchExecutor(ArchTaskExecutor.getMainThreadExecutor())
            .setNotifyExecutor(ArchTaskExecutor.getMainThreadExecutor()).build()
    }

    companion object {
        const val PAGE_SIZE: Int = 6
    }
}
