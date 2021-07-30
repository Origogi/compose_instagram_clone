package com.origogi.instgramclone.ui.components

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.imageResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.tagmanager.Container
import com.origogi.instgramclone.R

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun AnimatedToggleButton(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: @Composable () -> Unit,

    ) {
    IconToggleButton(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = { onCheckedChange(it) }
    ) {
        val transition = updateTransition(isChecked, label = "Checked indicator")

        val animatedSize by transition.animateDp(
            transitionSpec = {
                keyframes {
                    durationMillis = 250
                    10.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                    30.dp at 60 with FastOutLinearInEasing // for 15-50 ms
                    50.dp at 80 // ms
                    30.dp at 150 // ms
                }
            },
            label = "Size"
        ) {
            30.dp
        }

        Surface (
            color = Color.Transparent,
            modifier = Modifier.size(animatedSize),
        ) {
            icon()
        }
    }
}

@Preview("Favorite Button")
@Composable
fun AnimatedToggleButtonPreview() {
    val (isChecked, setChecked) = remember { mutableStateOf(false) }

    val icon =
        if (!isChecked) ImageBitmap.imageResource(R.drawable.ic_filled_favorite) else ImageBitmap.imageResource(
            R.drawable.ic_outlined_favorite
        )
    val tint = if (isChecked) Color.Red else MaterialTheme.colors.onSurface


    AnimatedToggleButton(
        isChecked = isChecked,
        onCheckedChange = { setChecked(it) },
        icon = {
            Icon(icon, tint = tint, contentDescription = "")
        }
    )

}