package com.origogi.instgramclone.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.R
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme

@Composable
fun InstagramHome() {
    InstgramcloneTheme {

        Scaffold(topBar = { Appbar() }) {

        }
    }


}

@Composable
fun Appbar() {
    TopAppBar(
        navigationIcon = null,
        title = {
            Image(
                painter = painterResource(id = R.drawable.instagram_logo),
                contentDescription = "",
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 0.dp,

        )

}

@Preview
@Composable
fun InstagramHomePreview() {
    InstagramHome()
}
