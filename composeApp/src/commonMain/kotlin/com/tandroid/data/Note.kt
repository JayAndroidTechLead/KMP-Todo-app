package com.tandroid.data

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Long,
    val title: String,
    val body: String,
    val createdAt: Long
)
