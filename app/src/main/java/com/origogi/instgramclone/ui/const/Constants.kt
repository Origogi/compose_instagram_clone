package com.origogi.instgramclone.ui.const

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.origogi.instgramclone.R

fun Modifier.icon() = this.size(24.dp)

enum class PageType(
    val icon: Int,
    val selectedIcon: Int
) {
    Home(R.drawable.ic_outlined_home, R.drawable.ic_filled_home),
    Add(R.drawable.ic_outlined_add, R.drawable.ic_outlined_add),
    Reels(R.drawable.ic_outlined_reels, R.drawable.ic_filled_reels),
    Favorite(R.drawable.ic_outlined_favorite, R.drawable.ic_filled_favorite),
    Profile(R.drawable.ic_outlined_reels, R.drawable.ic_outlined_reels)
}

