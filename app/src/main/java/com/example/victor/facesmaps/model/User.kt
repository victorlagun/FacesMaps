package com.example.victor.facesmaps.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val avatar: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    var page: Int,
    var total_pages: Int
)

fun convertUsers(users: List<User>, page: Int, total_pages: Int): List<User> {
     users.forEach {
        it.page = page
        it.total_pages = total_pages
    }
    return users
}