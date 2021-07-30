package com.origogi.instgramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.origogi.instgramclone.ui.const.PageType
import com.origogi.instgramclone.ui.const.icon
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme
import com.origogi.instgramclone.ui.view.InstagramHome
import com.origogi.instgramclone.ui.view.reels.InstagramReels
import com.origogi.instgramclone.ui.view.search.InstagramProfile
import com.origogi.instgramclone.ui.view.search.InstagramSearch
import com.origogi.instgramclone.ui.view.shop.InstagramShop
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramApp()
        }
    }
}

@Composable
fun InstagramApp() {
    val listState = rememberLazyListState()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    val currentScreen = PageType.fromRoute(
        backStackEntry.value?.destination?.route
    )

    val isDarkTheme = if (currentScreen == PageType.Reels) {
        true
    } else {
        isSystemInDarkTheme()
    }

    InstgramcloneTheme(
        darkTheme = isDarkTheme
    ) {
        Scaffold(
            topBar = {
                if (currentScreen != PageType.Reels) {
                    Appbar(listState)
                }
            },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    currentPage = currentScreen
                )
            }) { innerPadding ->

            Box(modifier = Modifier.padding(innerPadding)) {
                InstagramNavHost(navController = navController, listState = listState)
            }
        }
    }
}

@Composable
fun InstagramNavHost(navController: NavHostController, listState: LazyListState) {
    NavHost(navController = navController, startDestination = PageType.Home.name) {
        composable(PageType.Home.name) {
            InstagramHome(listState = listState)

        }
        composable(PageType.Reels.name) {
            InstagramReels()
        }
        composable(PageType.Search.name) {
            InstagramSearch()
        }
        composable(PageType.Shop.name) {
            InstagramShop()
        }
        composable(PageType.Profile.name) {
            InstagramProfile()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Appbar(listState: LazyListState) {
    val coroutineScope = rememberCoroutineScope()

    val iconSize = 25.dp
    val tint = MaterialTheme.colors.onSurface

    TopAppBar(
        navigationIcon = null,
        actions = {
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(10.dp)
                    .size(iconSize)
            ) {
                val icon = ImageBitmap.imageResource(R.drawable.ic_outlined_add)
                Icon(icon, contentDescription = "", tint = tint)
            }
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(10.dp)
                    .size(iconSize)
            ) {
                val icon = ImageBitmap.imageResource(R.drawable.ic_outlined_favorite)
                Icon(icon, contentDescription = "", tint = tint)
            }
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(10.dp)
                    .size(iconSize)
            ) {
                val icon = ImageBitmap.imageResource(R.drawable.ic_dm)
                Icon(icon, contentDescription = "", tint = tint)
            }
        },
        title = {
            Surface(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(index = 0)
                    }
                }, color = Color.Transparent
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.instagram_logo),
                    contentDescription = "",
                    tint = tint
                )
            }
        },

        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 0.dp,
    )
}

@Composable
fun BottomBar(navController: NavHostController, currentPage: PageType) {

    BottomNavigation(
        modifier = Modifier.height(50.dp),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = contentColorFor(MaterialTheme.colors.background)
    ) {
        PageType.values().forEach {
            BottomNavigationItem(
                icon = {

                    val iconRes = if (it == currentPage) {
                        it.selectedIcon
                    } else {
                        it.icon
                    }

                    Icon(
                        ImageBitmap.imageResource(id = iconRes),
                        contentDescription = "",
                        modifier = Modifier.icon(),
                        tint = MaterialTheme.colors.onSurface
                    )
                },
                selected = false,
                onClick = {
                    navController.navigate(it.name)
                },
                alwaysShowLabel = false
            )
        }
    }
}