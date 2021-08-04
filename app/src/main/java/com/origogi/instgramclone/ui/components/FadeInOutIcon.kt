package com.origogi.instgramclone.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SpeakerNotes
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FadeInOutSpeakerIcon(showIcon: Boolean, mute: Boolean) {



    AnimatedVisibility(
        visible = showIcon,
        enter = fadeIn(
        ),
        exit = fadeOut()
    ) {
        SpeakerIcon(mute = mute)
    }
}

@Composable
fun SpeakerIcon(mute: Boolean) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.7f))
    ) {
        Icon(
            if (mute) Icons.Filled.VolumeMute else Icons.Filled.VolumeUp,
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
@Preview
fun SpeakerIconPreview() {
    SpeakerIcon(mute = false)
}