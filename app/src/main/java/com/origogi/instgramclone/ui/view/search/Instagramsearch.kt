package com.origogi.instgramclone.ui.view.search

import android.media.MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.C.VIDEO_SCALING_MODE_SCALE_TO_FIT
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.origogi.instgramclone.R
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.ui.components.VideoPlayer
import com.origogi.instgramclone.ui.const.icon
import kotlin.random.Random

sealed class BaseItemData()

class VideoItemData(val videoUri: String) : BaseItemData()
class ImageItemData(val imageUri: Int, val isMoreImage : Boolean = false) : BaseItemData()

@Composable
fun InstagramSearch() {
    val scrollState = rememberScrollState()

    val items1 = listOf(
        VideoItemData("asset:///icecream.mp4"),
        ImageItemData(R.drawable.s1, true),
        ImageItemData(R.drawable.s2),
        ImageItemData(R.drawable.s3, true),
        ImageItemData(R.drawable.s4),
        ImageItemData(R.drawable.s5, true)
    )

    val items2 = listOf(
        ImageItemData(R.drawable.s1, true),
        ImageItemData(R.drawable.s5, true),
        ImageItemData(R.drawable.s4),
        ImageItemData(R.drawable.s3),
        VideoItemData("asset:///castle.mp4"),
        ImageItemData(R.drawable.s1, true)
    )

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        SearchAppbar()
        StaggeredItem1(items1)
        StaggeredItem2(items2)
        StaggeredItem3(items1)
        StaggeredItem4(items2)
    }
}

@Composable
fun StaggeredItem1(itemList: List<BaseItemData>) {
    Column() {
        Row() {
            SquareItem(data = itemList[0], modifier = Modifier.weight(1f))
            SquareItem(data = itemList[1], modifier = Modifier.weight(1f))
            SquareItem(data = itemList[2], modifier = Modifier.weight(1f))
        }
        Row() {
            SquareItem(data = itemList[3], modifier = Modifier.weight(1f))
            SquareItem(data = itemList[4], modifier = Modifier.weight(1f))
            SquareItem(data = itemList[5], modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StaggeredItem2(itemList: List<BaseItemData>) {
    Row() {
        SquareItem(data = itemList[0], modifier = Modifier.weight(2f))
        Column(Modifier.weight(1f)) {
            SquareItem(data = itemList[1], modifier = Modifier)
            SquareItem(data = itemList[2], modifier = Modifier)
        }
    }
}

@Composable
fun StaggeredItem3(itemList: List<BaseItemData>) {
    Row() {
        RectangleItem(data = itemList[0], modifier = Modifier.weight(1f))
        Column(Modifier.weight(2f)) {
            Row() {
                SquareItem(data = itemList[1], modifier = Modifier.weight(1f))
                SquareItem(data = itemList[2], modifier = Modifier.weight(1f))

            }
            Row() {
                SquareItem(data = itemList[3], modifier = Modifier.weight(1f))
                SquareItem(data = itemList[4], modifier = Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun StaggeredItem4(itemList: List<BaseItemData>) {
    Row() {
        Column(Modifier.weight(2f)) {
            Row() {
                SquareItem(data = itemList[0], modifier = Modifier.weight(1f))
                SquareItem(data = itemList[1], modifier = Modifier.weight(1f))

            }
            Row() {
                SquareItem(data = itemList[2], modifier = Modifier.weight(1f))
                SquareItem(data = itemList[3], modifier = Modifier.weight(1f))
            }
        }
        RectangleItem(data = itemList[4], modifier = Modifier.weight(1f))
    }
}


@Composable
fun SquareItem(data: BaseItemData, modifier: Modifier) {
    val itemModifier = modifier
        .aspectRatio(1f)
        .padding(1.dp)

    Item(data = data, itemModifier)
}

@Composable
fun RectangleItem(data: BaseItemData, modifier: Modifier) {
    val itemModifier = modifier
        .aspectRatio(0.5f)
        .padding(1.dp)


    Item(data = data, itemModifier, false)
}

@Composable
fun Item(data: BaseItemData, modifier: Modifier, isSquare: Boolean = true) {

    when (data) {
        is ImageItemData -> Box(
            modifier = modifier,
        ) {
            Image(
                modifier = modifier,
                painter = painterResource(id = data.imageUri),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            if (data.isMoreImage) {
                Icon(
                    Icons.Default.ContentCopy,
                    tint = Color.White,
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )
            }
        }
        is VideoItemData ->

            VideoPlayer(
                modifier = modifier,
                sourceUrl = data.videoUri,
                enableAutoplay = true,
                mute = true,
                resizeMode = if (isSquare) AspectRatioFrameLayout.RESIZE_MODE_ZOOM else AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT
            )
    }
}

@Composable
fun SearchAppbar() {
    Surface(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(20))

    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Filled.Search, contentDescription = "", modifier = Modifier.icon())
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "??????", style = MaterialTheme.typography.body2, color = Color.Gray
            )
        }
    }
}
//
//@Composable
//@Preview
//fun StaggeredItemOnePreview() {
//    val items = listOf(
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1)
//    )
//    StaggeredItem1(itemList = items)
//}
//
//@Composable
//@Preview
//fun StaggeredItemTwoPreview() {
//    val items = listOf(
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1)
//    )
//    StaggeredItem2(itemList = items)
//}
//
//@Composable
//@Preview
//fun StaggeredItemThreePreview() {
//    val items = listOf(
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1)
//    )
//    StaggeredItem3(itemList = items)
//}
//
//@Composable
//@Preview
//fun StaggeredItemFourPreview() {
//    val items = listOf(
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1),
//        ImageItemData(R.drawable.s1)
//    )
//    StaggeredItem4(itemList = items)
//}

@Composable
@Preview
fun RectangleImageViewPreview() {
    RectangleItem(data = ImageItemData(R.drawable.s1, true), modifier = Modifier)

}


@Composable
@Preview
fun SearchAppbarPreview() {
    SearchAppbar()

}