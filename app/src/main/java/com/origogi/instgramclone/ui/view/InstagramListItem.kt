package com.origogi.instgramclone.ui.view

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.constraintlayout.widget.ConstraintLayout
import com.origogi.instgramclone.R
import com.origogi.instgramclone.ui.components.AnimatedToggleButton
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InstagramListItem(post: Story) {

    var editMessageShown by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


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
                EditMessage(editMessageShown)

            }
        }
        InstagramIconSection(post, onBookmarkClick = {
            coroutineScope.launch {
                showEditMessage()
            }
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
fun InstagramListItemTest(post: Story) {

    var editMessageShown by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    suspend fun showEditMessage() {
        if (!editMessageShown) {
            editMessageShown = true
            delay(3000L)
            editMessageShown = false
        }
    }

    Column() {
        ProfileInfoSection(post)
        BoxWithConstraints() {
            EditMessage(editMessageShown)

            InstagramImage(imageId = post.storyImageId)
        }
        Column {
            InstagramIconSection(post, onBookmarkClick = {
                coroutineScope.launch {
                    showEditMessage()
                }
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
private fun InstagramIconSection(post: Story, onBookmarkClick: () -> Unit) {
    var bookmark by remember { mutableStateOf(false) }
    var fav by remember { mutableStateOf(post.isLiked) }


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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
        AnimatedToggleButton(bookmark,
            onCheckedChange = {
                bookmark = it
                onBookmarkClick()
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
private fun EditMessage(shown: Boolean) {

    AnimatedVisibility(
        visible = shown,
        enter = expandVertically(
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp
        ) {
            Text(
                text = "테스트 메시지입니다.",
                modifier = Modifier.padding(16.dp)
            )
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
fun InstagramListItemPreview() {
    MaterialTheme {
        InstagramListItem(post = DataDummy.storyList[4])
    }
}
