package com.origogi.instgramclone.data

data class Reel(
    val videoUri: String,
    val likesCount: Int,
    val commentsCount: Int,
    val authorImageId: Int,
    val author: String,
    val singTitle : String,
    val description : String
)