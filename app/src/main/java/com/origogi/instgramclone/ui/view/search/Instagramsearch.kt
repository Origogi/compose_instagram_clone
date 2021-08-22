package com.origogi.instgramclone.ui.view.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.solver.widgets.Rectangle
import com.origogi.instgramclone.R
import com.origogi.instgramclone.ui.const.icon

@Composable
fun InstagramSearch() {

    val scrollState = rememberScrollState()


    Column(modifier = Modifier.verticalScroll(scrollState)) {
        SearchAppbar()
        StaggeredItem1()
        StaggeredItem2()
        StaggeredItem3()
        StaggeredItem4()
    }
}

@Composable
fun StaggeredItem1() {
    Column() {
        Row() {
            SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
            SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
            SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
        }
        Row() {
            SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
            SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
            SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StaggeredItem2() {
    Row() {
        SquareImage(id = R.drawable.s1, modifier = Modifier.weight(2f))
        Column(Modifier.weight(1f)) {
            SquareImage(id = R.drawable.s1, modifier = Modifier)
            SquareImage(id = R.drawable.s1, modifier = Modifier)
        }
    }
}

@Composable
fun StaggeredItem3() {
    Row() {
        RectangleImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
        Column(Modifier.weight(2f)) {
            Row() {
                SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
                SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))

            }
            Row() {
                SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
                SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun StaggeredItem4() {
    Row() {
        Column(Modifier.weight(2f)) {
            Row() {
                SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
                SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))

            }
            Row() {
                SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
                SquareImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
            }
        }
        RectangleImage(id = R.drawable.s1, modifier = Modifier.weight(1f))
    }
}


@Composable
fun SquareImage(id : Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "",
        modifier = modifier
            .aspectRatio(1f)
            .padding(5.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun RectangleImage(id : Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "",
        modifier = modifier
            .aspectRatio(0.5f)
            .padding(5.dp),
        contentScale = ContentScale.Crop
    )
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
fun StaggeredItemOnePreview() {
    StaggeredItem1()
}

@Composable
@Preview
fun StaggeredItemTwoPreview() {
    StaggeredItem2()
}

@Composable
@Preview
fun StaggeredItemThreePreview() {
    StaggeredItem3()
}

@Composable
@Preview
fun StaggeredItemFourPreview() {
    StaggeredItem4()
}

@Composable
@Preview
fun RectangleImageViewPreview() {
    RectangleImage(id = R.drawable.s1, modifier = Modifier)

}


@Composable
@Preview
fun SearchAppbarPreview() {
    SearchAppbar()

}