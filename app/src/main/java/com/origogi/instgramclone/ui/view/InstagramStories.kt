package com.origogi.instgramclone.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.data.Story
import com.origogi.instgramclone.ui.theme.instagramGradient
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun InstagramStories() {

    val posts = remember {
        DataDummy.storyList
    }

    LazyRow(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
        items(posts) {
            StoryListItem(post = it)
        }
    }
}

@Composable
fun StoryListItem(
    post: Story, modifier: Modifier = Modifier
        .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        .height(60.dp)
        .width(60.dp)
        .clip(CircleShape)
) {

    val imageModifier =
        if (post.id == 1) {
            modifier
                .border(
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 3.dp,
                        color = Color.LightGray
                    )
                )
        } else {
            modifier
                .border(
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 3.dp,
                        brush = Brush.linearGradient(
                            colors = instagramGradient,
                            start = Offset(
                                0f,
                                0f
                            ),
                            end = Offset(
                                100f,
                                100f
                            )
                        )
                    )
                )
        }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = post.authorImageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = imageModifier
        )
        Text(text = post.author, style = typography.caption, textAlign = TextAlign.Center)
    }

}

@Preview
@Composable
fun InstagramStoriesPreview() {
    MaterialTheme {
        InstagramStories()
    }
}
