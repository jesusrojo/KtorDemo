package com.jesusrojo.ktorserver.model


data class Request(
    val query: String,
    val page: Int,
    val id: String,
    val isUnderAged: Boolean
)
