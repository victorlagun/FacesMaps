package com.example.victor.facesmaps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.victor.facesmaps.model.User
import com.example.victor.facesmaps.repository.impl.RepositoryImpl

class UserViewModel : ViewModel() {
    fun getUser(id: Int): LiveData<User?> {
        return RepositoryImpl.getUser(id)
    }
}
