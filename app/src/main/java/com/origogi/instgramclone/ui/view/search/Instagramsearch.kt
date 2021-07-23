package com.origogi.instgramclone.ui.view.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InstagramSearch() {
    Column() {
        Text(text = "Search")
    }
}

@Composable
@Preview
fun InstagramSearchPreview() {
    InstagramSearch()
}