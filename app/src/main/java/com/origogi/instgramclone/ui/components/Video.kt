package com.origogi.instgramclone.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.origogi.instgramclone.ui.view.reels.noRippleClickable


@Composable
fun VideoPlayer(
    modifier: Modifier,
    sourceUrl: String,
    enableAutoplay: Boolean,
    mute: Boolean,
    resizeMode : Int = AspectRatioFrameLayout.RESIZE_MODE_FIT,
    videoScalingMode: Int = VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING,
    videoClick: (() -> Unit)? = null
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context)
            .build()
            .apply {
                val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                    context,
                    Util.getUserAgent(context, context.packageName)
                )

                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(
                        Uri.parse(
                            sourceUrl
                        )
                    )

                this.prepare(source)
                this.videoScalingMode = videoScalingMode
                repeatMode = Player.REPEAT_MODE_ONE
            }
    }


    DisposableEffect(
        AndroidView(modifier = modifier
        .noRippleClickable {
            videoClick?.invoke()
        },
        factory = {
            PlayerView(context).apply {
                hideController()
                useController = false
                this.resizeMode = resizeMode
                player = exoPlayer

            }
        }, update = {
            exoPlayer.playWhenReady = enableAutoplay
            exoPlayer.volume = if (mute) 0f else 1f
        })
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}