package com.origogi.instgramclone.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedWaveIcon() {

    val infiniteTransition = rememberInfiniteTransition()
    val v = infiniteTransition.animateFloat(
        initialValue = 5f, targetValue = 10f, animationSpec = infiniteRepeatable(
            animation = tween(700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val height = v.value
    val reverseHeight = 15f - height

    print("$height/$reverseHeight")



    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.size(height = 12.dp, width = 11.dp)
    ) {
        Bar(height = height)
        Spacer(modifier = Modifier.size(2.dp))
        Bar(height = reverseHeight)
        Spacer(modifier = Modifier.size(2.dp))
        Bar(height = height)
    }
    
}

@Composable
fun Bar(height: Float) {
    Box(
        modifier = Modifier
            .size(width = 2.dp, height = (height).dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.onSurface)
    )
}

@Composable
@Preview
fun AnimatedWaveIconPreview() {
    AnimatedWaveIcon()
}

