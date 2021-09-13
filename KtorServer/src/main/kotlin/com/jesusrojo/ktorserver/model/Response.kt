package com.jesusrojo.ktorappwithserver.model

import com.jesusrojo.ktorserver.model.RawData

data class Response (
    val status: String,
    val items: List<RawData>,
    val hasMore: Boolean?
)