package com.origogi.instgramclone.ui.view

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.data.Story
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import com.origogi.instgramclone.R
import com.origogi.instgramclone.ui.components.AnimatedToggleButton
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.origogi.instgramclone.ui.theme.Blue100

@Composable
fun InstagramListItem(post: Story) {

    var editMessageShown by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var bookmark by remember { mutableStateOf(false) }

    suspend fun showEditMessage() {
        if (!editMessageShown) {
            editMessageShown = true
            delay(3000L)
            editMessageShown = false
        }
    }

    Column() {
        ProfileInfoSection(post)
        Box {
            InstagramImage(imageId = post.storyImageId)
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                BookMarkMessage(editMessageShown, post = post)

            }
        }
        InstagramIconSection(post, bookmark, onBookmarkClick = {
            if (it) {
                coroutineScope.launch {
                    showEditMessage()
                }
            }
            bookmark = it
        })
        InstagramLikeSection(post = post)
        Text(
            text = "View all ${post.commentsCount} comments",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            color = Color.Gray
        )
        Text(
            text = "${post.time} ago",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 8.dp),
            color = Color.Gray
        )
    }
}

@Composable
fun InstagramImage(imageId: Int) {
    if (imageId != 0) {
        Image(
            painter = painterResource(id = imageId),
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun InstagramIconSection(
    post: Story,
    bookmark: Boolean,
    onBookmarkClick: (Boolean) -> Unit
) {
    var fav by remember { mutableStateOf(post.isLiked) }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Row {

            AnimatedToggleButton(isChecked = fav,
                onCheckedChange = {
                    fav = it
                    post.isLiked = it
                }) {

                val icon =
                    if (fav) ImageBitmap.imageResource(R.drawable.ic_filled_favorite) else ImageBitmap.imageResource(
                        R.drawable.ic_outlined_favorite
                    )
                val tint = if (fav) Color.Red else MaterialTheme.colors.onSurface
                Icon(icon, contentDescription = "", tint = tint)

            }
            IconToggleButton(checked = false,
                onCheckedChange = { }) {

                val icon = ImageBitmap.imageResource(R.drawable.ic_outlined_comment)
                val tint = MaterialTheme.colors.onSurface
                Icon(icon, contentDescription = "", tint = tint)

            }
            IconToggleButton(false,
                onCheckedChange = { }) {

                val icon = ImageBitmap.imageResource(R.drawable.ic_dm)
                val tint = MaterialTheme.colors.onSurface
                Icon(icon, contentDescription = "", tint = tint)

            }
        }
        AnimatedToggleButton(
            isChecked = bookmark,
            onCheckedChange = {
                onBookmarkClick(it)
            }) {

            val icon =
                ImageVector.vectorResource(if (bookmark) R.drawable.ic_filled_bookmark else R.drawable.ic_outlined_bookmark)

            val tint = MaterialTheme.colors.onSurface
            Icon(icon, contentDescription = "", tint = tint)

        }

    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun BookMarkMessage(shown: Boolean, post: Story) {

    AnimatedVisibility(
        visible = shown,
        enter = expandVertically(
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top
        )
    ) {
        Surface(color = MaterialTheme.colors.surface) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = post.storyImageId),
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "저장됨", style = MaterialTheme.typography.body2,
                    )
                }
                Text(
                    text = "컬랙션에 저장", style = MaterialTheme.typography.body2,
                    color = Blue100
                )

            }
        }

    }
}


@Composable
fun ProfileInfoSection(post: Story) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = post.authorImageId),
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            text = post.author,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            textAlign = TextAlign.Left
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = ""
        )
    }

}

@Composable
fun InstagramLikeSection(post: Story) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = post.authorImageId),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Liked by ${post.author} and ${post.likesCount} others",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun BookmarkMessagePreview() {
    InstgramcloneTheme {
        BookMarkMessage(post = DataDummy.storyList[4], shown = true)

    }
}

@Preview
@Composable
fun InstagramListItemPreview() {
    MaterialTheme {
        InstagramListItem(post = DataDummy.storyList[4])
    }
}
