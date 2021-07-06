package com.origogi.instgramclone.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox

import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.R
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme

@Composable
fun InstagramHome() {
    InstgramcloneTheme {

        Scaffold(topBar = { Appbar() }) {
            InstagramHomeContents()
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Appbar() {
    TopAppBar(
        navigationIcon = null,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.AddBox, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Send, contentDescription = "")
            }
        },
        title = {
            Surface(onClick = {
                //TODO : Move first item on List View
            }) {
                Image(
                    painter = painterResource(id = R.drawable.instagram_logo),
                    contentDescription = "",
                )

            }


        },

        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 0.dp,

        )

}

@Composable
fun InstagramHomeContents() {
    Column {
        InstagramStories()
        Divider()
        InstagramPostList()
        
    }
}

@Composable
fun InstagramPostList() {
    val posts = DataDummy.storyList.filter { it.storyImageId != 0 }
    LazyColumn {
        items( items = posts) { post ->
            InstagramListItem(post = post)
        }
    }
}


@Preview
@Composable
fun InstagramHomePreview() {
    InstagramHome()
}
