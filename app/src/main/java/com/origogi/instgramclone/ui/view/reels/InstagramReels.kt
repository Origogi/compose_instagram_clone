package com.origogi.instgramclone.ui.view.reels

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.R
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme

@Composable
fun InstagramReels() {

    Surface {
        Row {
            ReelsTopbar()
        }
    }

}

@Composable
fun ReelsTopbar(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Reels", style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold))
        Icon(modifier= modifier.size(20.dp),
            painter = painterResource(id = R.drawable.ic_outlined_camera), contentDescription = "")
    }
}

@Composable
fun VerticalButton() {
    Column {

    }
}

@Composable
@Preview(showBackground = true)
fun InstagramReelsPreview() {

    InstgramcloneTheme(darkTheme = true) {
        InstagramReels()

    }
}