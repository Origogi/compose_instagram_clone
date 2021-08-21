package com.origogi.instgramclone

import android.app.Activity
import android.os.Bundle
import android.view.View
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.exoplayer2.upstream.DummyDataSource
import com.origogi.instgramclone.data.DataDummy
import com.origogi.instgramclone.ui.const.PageType
import com.origogi.instgramclone.ui.const.icon
import com.origogi.instgramclone.ui.theme.InstgramcloneTheme
import com.origogi.instgramclone.ui.view.InstagramHome
import com.origogi.instgramclone.ui.view.reels.InstagramReels
import com.origogi.instgramclone.ui.view.search.InstagramProfile
import com.origogi.instgramclone.ui.view.search.InstagramSearch
import com.origogi.instgramclone.ui.view.shop.InstagramShop
import kotlinx.coroutines.launch
import com.origogi.instgramclone.ui.*




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val decorView = window.decorView //set status background black

        decorView.systemUiVisibility =
            decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        window.statusBarColor = ContextCompat.getColor(this, R.color.black);

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

    val activity = LocalContext.current as Activity


    val currentScreen = PageType.fromRoute(
        backStackEntry.value?.destination?.route
    )

    val isDarkTheme = if (currentScreen == PageType.Reels) {
        true
    } else {
        isSystemInDarkTheme()


    }

    if (currentScreen == PageType.Reels) {
        val decorView = activity.window.decorView //set status background black
        decorView.systemUiVisibility =
            decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.black);
    } else {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.white);
    }

    InstgramcloneTheme(
        darkTheme = isDarkTheme
    ) {
        Scaffold(
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
            InstagramReels(reels = DataDummy.reels)
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
                    navController.navigate(it.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }

                },
                alwaysShowLabel = false
            )
        }
    }
}