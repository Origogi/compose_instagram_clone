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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.R
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.ui.const.PageType
import com.origogi.instgramclone.ui.const.icon
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme
import kotlinx.coroutines.launch





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

