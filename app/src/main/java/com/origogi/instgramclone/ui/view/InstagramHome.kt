package com.origogi.instgramclone.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox

import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.R
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme
import kotlinx.coroutines.launch

@Composable
fun InstagramHome() {
    InstgramcloneTheme {

        val listState = rememberLazyListState()

        Scaffold(topBar = { Appbar(listState) }) {
            InstagramHomeContents(listState)
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Appbar(listState: LazyListState) {
    val coroutineScope = rememberCoroutineScope()

    val iconSize = 25.dp
    val tint = MaterialTheme.colors.onSurface

    TopAppBar(
        navigationIcon = null,
        actions = {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp).size(iconSize)) {
                val icon = ImageBitmap.imageResource(R.drawable.ic_outlined_add)
                Icon(icon, contentDescription = "", tint = tint)
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp).size(iconSize)) {
                val icon = ImageBitmap.imageResource(R.drawable.ic_outlined_favorite)
                Icon(icon, contentDescription = "", tint = tint)
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp).size(iconSize)) {
                val icon = ImageBitmap.imageResource(R.drawable.ic_dm)
                Icon(icon, contentDescription = "", tint = tint)
            }
        },
        title = {
            Surface(onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(index = 0)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.instagram_logo),
                    contentDescription = "",
                    tint = tint
                )
            }
        },

        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 0.dp,

        )

}

@Composable
fun InstagramHomeContents(listState: LazyListState) {
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


@Preview
@Composable
fun InstagramHomePreview() {
    InstagramHome()
}
