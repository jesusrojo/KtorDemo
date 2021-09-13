package com.jesusrojo.ktorappwithserver.model

data class Response (
    val status: String,
    val items: List<RawData>,
    val hasMore: Boolean?
)