package com.jesusrojo.ktorappwithserver.model


data class Request(
    val query: String,
    val page: Int,
    val id: String,
    val isUnderAged: Boolean
)
