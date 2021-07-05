package com.origogi.instgramclone.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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

@Composable
fun InstagramListItem(post: Story) {
    Column() {
        ProfileInfoSection(post)
        InstagramImage(imageId = post.storyImageId)
        InstagramIconSection()
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
private fun InstagramIconSection() {
    Row {
        var fav by remember { mutableStateOf(false) }
        IconToggleButton(checked = fav,
            onCheckedChange = { fav = it }) {

            val icon = if (fav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
            val tint = if (fav) Color.Red else MaterialTheme.colors.onSurface
            Icon(icon, contentDescription = "", tint = tint)

        }
        IconToggleButton(checked = false,
            onCheckedChange = { }) {

            val icon = Icons.Default.ChatBubbleOutline
            val tint = MaterialTheme.colors.onSurface
            Icon(icon, contentDescription = "", tint = tint)

        }
        IconToggleButton(false,
            onCheckedChange = {  }) {

            val icon = Icons.Filled.Send
            val tint = MaterialTheme.colors.onSurface
            Icon(icon, contentDescription = "", tint = tint)

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

@Preview
@Composable
fun InstagramListItemPreview() {
    MaterialTheme {
        InstagramListItem(post = DataDummy.storyList[4])
    }
}
