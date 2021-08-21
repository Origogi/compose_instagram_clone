package com.origogi.instgramclone.ui.view.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InstagramProfile() {
    Column {
        ProfileAppbar()
    }
}

@Composable
fun ProfileAppbar() {
    Surface(modifier = Modifier
        .background(color = Color.Gray)
        .fillMaxWidth()
        .height(20.dp)) {

    }
}

@Composable
@Preview
fun InstagramProfilePreview() {
    InstagramProfile()
}