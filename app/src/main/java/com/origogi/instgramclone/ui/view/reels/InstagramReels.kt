package com.origogi.instgramclone.ui.view.reels

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState

import com.origogi.instgramclone.R
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.data.Reel
import com.origogi.instgramclone.ui.components.*
import com.origogi.instgramclone.ui.const.icon
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var job: Job? = null

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InstagramReels(reels: List<Reel>) {

    val pagerState = rememberPagerState(
        pageCount = reels.size,
    )

    var muteState by remember {
        mutableStateOf(false)
    }

    var showSpeakerIconState by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()


    suspend fun changeMuteState() {
        muteState = !muteState
        showSpeakerIconState = true
        delay(2000)
        showSpeakerIconState = false

    }
    Box {
        VerticalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()

        ) { page ->
            ReelItem(
                reel = DataDummy.reels[page],
                selected = page == pagerState.currentPage,
                mute = muteState,
            ) {
                job?.cancel()
                job = coroutineScope.launch {
                    changeMuteState()
                }
            }
        }

        Row(modifier = Modifier.align(Alignment.TopCenter)) {
            ReelsTopbar()
        }

        Box(modifier = Modifier.align(Alignment.Center)) {
            FadeInOutSpeakerIcon(showIcon = showSpeakerIconState, mute = muteState)

        }
    }
}

@Composable
fun ReelItem(reel: Reel, selected: Boolean, mute: Boolean, videoClick: () -> Unit) {
    Box {
        VideoPlayer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            reel.videoUri,
            enableAutoplay = selected,
            mute,
            videoClick = videoClick
        )

        Box(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            VerticalButtons(reel = reel)
        }
        Box(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            ProfileDescription(reel = reel)

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
        Text(
            text = "Reels",
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
        )
        Icon(
            modifier = Modifier.icon(),
            painter = painterResource(id = R.drawable.ic_outlined_camera), contentDescription = ""
        )
    }
}

@Composable
fun VerticalButtons(reel: Reel) {

    val (isChecked, setChecked) = remember { mutableStateOf(false) }

    val icon =
        if (isChecked) ImageBitmap.imageResource(R.drawable.ic_filled_favorite) else ImageBitmap.imageResource(
            R.drawable.ic_outlined_favorite
        )
    val tint = if (isChecked) Color.Red else MaterialTheme.colors.onSurface

    Column(
        modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedToggleButton(
            modifier = Modifier.icon(),
            isChecked = isChecked,
            onCheckedChange = {
                setChecked(it)
            }) {
            Icon(icon, tint = tint, contentDescription = "")
        }
        Text(
            text = "%,d".format(reel.likesCount),
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
            text = "%,d".format(reel.commentsCount),
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
        Spacer(modifier = Modifier.size(height = 30.dp, width = 0.dp))

        Image(
            painter = painterResource(reel.authorImageId),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,            // crop the image if it's not a square
            modifier = Modifier
                .icon()
                .clip(RoundedCornerShape(20))                       // clip to the circle shape
                .border(2.dp, Color.White, RoundedCornerShape(20))   // add a border (optional)
        )

    }
}

@Composable
fun ProfileDescription(reel: Reel) {
    Column(
        modifier = Modifier.padding(15.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(reel.authorImageId),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)                       // clip to the circle shape
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = reel.author, style = MaterialTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Dot()
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "?????????", style = MaterialTheme.typography.subtitle2.copy(
                    color = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = reel.description, style = MaterialTheme.typography.subtitle2.copy(
                    color = Color.White

                )
            )
            Text(
                text = " ... ?????????",
                style = MaterialTheme.typography.subtitle2.copy(color = Color.LightGray)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(2.dp))

            AnimatedWaveIcon()
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = reel.singTitle, style = MaterialTheme.typography.subtitle2.copy(
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Dot()
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "?????? ?????????", style = MaterialTheme.typography.subtitle2.copy(
                    color = Color.White
                )
            )
        }
    }
}


@Composable
@Preview
fun ProfileDescriptionPreview() {
    ProfileDescription(reel = DataDummy.reels[0])
}

@Composable
@Preview
fun VerticalButtonPreview() {
    VerticalButtons(reel = DataDummy.reels[0])
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}