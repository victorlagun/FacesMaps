package com.example.victor.facesmaps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.victor.facesmaps.App
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.paging.UserBoundaryCallback
import java.util.concurrent.Executors


class UsersListViewModel : ViewModel() {

    fun getPagedList(): LiveData<PagedList<User>> {
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PAGE_SIZE)
                .build()

            return LivePagedListBuilder(App.instance.database.userDao().getAll(), config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setBoundaryCallback(UserBoundaryCallback())
                .build()
    }

    companion object {
        const val PAGE_SIZE: Int = 6
    }
}
