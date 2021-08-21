package com.origogi.instgramclone.ui.view.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.ui.const.icon

@Composable
fun InstagramSearch() {

    Column() {
        SearchAppbar()
    }
}

@Composable
fun SearchAppbar() {
    Surface(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(20))

    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Filled.Search, contentDescription = "", modifier = Modifier.icon())
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "검색", style = MaterialTheme.typography.body2, color = Color.Gray
            )
        }
    }
}

@Composable
@Preview
fun InstagramSearchPreview() {
    InstagramSearch()
}