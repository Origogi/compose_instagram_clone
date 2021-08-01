package com.origogi.instgramclone.ui.view.reels

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.origogi.instgramclone.R
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.data.Reel
import com.origogi.instgramclone.ui.components.AnimatedToggleButton
import com.origogi.instgramclone.ui.const.icon
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InstagramReels() {

    val pagerState = rememberPagerState(
        pageCount = DataDummy.reels.size,
    )

    VerticalPager(
        state = pagerState, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { page ->
        ReelItem(
            reel = DataDummy.reels[page],
            selected = page == pagerState.currentPage
        )
    }

    Row {
        ReelsTopbar()
    }
}

@Composable
fun ReelItem(reel: Reel, selected: Boolean) {
    Box {
        VideoPlayer(reel.videoUri, enableAutoplay = selected)

        Box(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            VerticalButton()
        }
    }
}

@Composable
fun ReelsTopbar(modifier: Modifier = Modifier) {

    Row(
        modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Reels", style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold))
        Icon(
            modifier = Modifier.icon(),
            painter = painterResource(id = R.drawable.ic_outlined_camera), contentDescription = ""
        )
    }
}

@Composable
fun VerticalButton() {

    val (isChecked, setChecked) = remember { mutableStateOf(false) }

    val icon =
        if (!isChecked) ImageBitmap.imageResource(R.drawable.ic_filled_favorite) else ImageBitmap.imageResource(
            R.drawable.ic_outlined_favorite
        )
    val tint = if (isChecked) Color.Red else MaterialTheme.colors.onSurface

    Column(
        modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedToggleButton(
            modifier = Modifier.icon(),
            isChecked = false,
            onCheckedChange = {
                // TODO
            }) {
            Icon(icon, tint = tint, contentDescription = "")
        }
        Text(
            text = "1,078",
            modifier = Modifier.padding(top = 5.dp),
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))
        Icon(
            modifier = Modifier.icon(),
            painter = painterResource(id = R.drawable.ic_outlined_comment),
            contentDescription = ""
        )
        Text(
            text = "1,245",
            modifier = Modifier.padding(top = 5.dp),
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))
        Icon(
            modifier = Modifier.icon(),
            painter = painterResource(id = R.drawable.ic_dm),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(height = 30.dp, width = 0.dp))
        Icon(
            Icons.Filled.MoreVert, contentDescription = "",
            modifier = Modifier.icon(),
        )
    }
}

@Composable
fun VideoPlayer(
    sourceUrl: String,
    enableAutoplay: Boolean
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
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
                repeatMode = Player.REPEAT_MODE_ONE
            }
    }

    DisposableEffect(AndroidView(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        factory = {
            PlayerView(context).apply {
                hideController()
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                player = exoPlayer

            }
        }, update = {
            exoPlayer.playWhenReady = enableAutoplay
        })
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}


@Composable
@Preview
fun VerticalButtonPreview() {
    VerticalButton()
}

@Composable
@Preview(showBackground = true)
fun InstagramReelsPreview() {

    InstgramcloneTheme(darkTheme = true) {
        InstagramReels()

    }
}