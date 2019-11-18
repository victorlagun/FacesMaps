package com.example.victor.facesmaps.model

data class Page(
    val data: List<User>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)