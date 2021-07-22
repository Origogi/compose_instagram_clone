package com.origogi.instgramclone.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items

import androidx.compose.material.*
import androidx.compose.runtime.*
import com.origogi.instgramclone.data.DataDummy

@Composable
fun InstagramHome(listState: LazyListState) {
    Column {
        InstagramStories()
        Divider()
        InstagramPostList(listState)

    }
}

@Composable
fun InstagramPostList(listState: LazyListState) {
    val posts = DataDummy.storyList.filter { it.storyImageId != 0 }

    LazyColumn(state = listState) {
        items(items = posts) { post ->
            InstagramListItem(post = post)
        }
    }
}

