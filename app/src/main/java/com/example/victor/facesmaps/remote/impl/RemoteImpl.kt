package com.example.victor.facesmaps.remote.impl

import com.example.victor.facesmaps.model.Page
import com.example.victor.facesmaps.remote.Remote
import io.reactivex.Observable

class RemoteImpl(private val remote: Remote) {

    fun users(page: Int): Observable<Page> {
        return remote.users(page)
    }
}