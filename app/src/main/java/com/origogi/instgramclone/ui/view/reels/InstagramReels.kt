package com.origogi.instgramclone.ui.view.reels

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.R
import com.origogi.instgramclone.ui.components.AnimatedToggleButton
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme

@Composable
fun InstagramReels(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row {
            ReelsTopbar(modifier)
        }
    }

}

@Composable
fun ReelsTopbar(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Reels", style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold))
        Icon(painter = painterResource(id = R.drawable.ic_outlined_camera), contentDescription = "")
    }
}

@Composable
fun VerticalButton() {

    val (isChecked, setChecked) = remember { mutableStateOf(false) }

    val icon =
        if (!isChecked) ImageBitmap.imageResource(R.drawable.ic_filled_favorite) else ImageBitmap.imageResource(
            R.drawable.ic_outlined_favorite
        )
    val tint = if (isChecked) Color.Red else MaterialTheme.colors.onSurface

    Column(
        modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedToggleButton(modifier = Modifier.size(30.dp), isChecked = false, onCheckedChange = {
            // TODO
        }) {
            Icon(icon, tint = tint, contentDescription = "")
        }
        Text(text = "1,078")
        Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_outlined_comment),
            contentDescription = ""
        )
        Text(text = "2,685")
        Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_dm),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(height = 30.dp, width = 0.dp))
        Icon(
            Icons.Filled.MoreVert, contentDescription = "", modifier = Modifier.size(30.dp),
        )

    }
}

@Composable
@Preview
fun VerticalButtonPreview() {
    VerticalButton()
}

@Composable
@Preview(showBackground = true)
fun InstagramReelsPreview() {

    InstgramcloneTheme(darkTheme = true) {
        InstagramReels()

    }
}