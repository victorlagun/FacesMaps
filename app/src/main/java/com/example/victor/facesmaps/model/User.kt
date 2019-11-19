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
    val last_name: String
)