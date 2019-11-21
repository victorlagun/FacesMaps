package com.example.victor.facesmaps.paging

import androidx.paging.PagedList
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.repository.impl.RepositoryImpl

class UserBoundaryCallback : PagedList.BoundaryCallback<User>() {
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        RepositoryImpl.getAll()
    }

    override fun onItemAtEndLoaded(itemAtEnd: User) {
        super.onItemAtEndLoaded(itemAtEnd)
        RepositoryImpl.getAll()
    }
}