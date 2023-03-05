@file:OptIn(ExperimentalFoundationApi::class)

package com.merttoptas.diaryapp.features.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.merttoptas.diaryapp.core.data.model.Diary
import com.merttoptas.diaryapp.domain.state.ScreenState
import com.merttoptas.diaryapp.features.components.*
import java.time.LocalDate

/**
 * Created by mertcantoptas on 18.02.2023
 */

@Composable
fun HomeScreen(
    modifier: Modifier,
    screenState: ScreenState<HomeUiState>,
    onLogoutClicked: () -> Unit,
    drawerState: DrawerState,
    onMenuClicked: () -> Unit
) {
    HomeDrawerMenu(drawerState = drawerState, onSignOutClick = onLogoutClicked) {
        DiaryScaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                HomeTopBar(onMenuClicked = onMenuClicked, onDateRangeClicked = { /*TODO*/ })
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "New Diary")
                }
            },
        ) {
            Box(modifier = modifier.fillMaxSize()) {
                when (screenState) {
                    is ScreenState.Loading -> Unit
                    is ScreenState.Success -> {
                        Content(mapOf(), {})
                    }
                    is ScreenState.Error -> {
                        //TODO: Handle error
                    }
                }
            }
        }
    }
}

@Composable
private fun Content(
    diaryNotes: Map<LocalDate, List<Diary>>,
    onClick: (String) -> Unit
) {
    if (diaryNotes.isNotEmpty()) {
        LazyColumn(modifier = Modifier.padding(horizontal = 24.dp)) {
            diaryNotes.forEach { (localDate, diaries) ->
                stickyHeader(key = localDate) {
                    DiaryDateHeader(localDate = localDate)
                }
                items(items = diaries, key = { it._id }) {
                    DiaryHolder(diary = it, onClick = onClick)
                }
            }
        }
    } else {
        EmptyPage()
    }
}

@Composable
fun EmptyPage(
    title: String = "Empty Diary",
    subtitle: String = "Write Something"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiaryEmptyPageAnimation()
        Text(
            text = title,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = subtitle,
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
private fun HomeTopBar(onMenuClicked: () -> Unit, onDateRangeClicked: () -> Unit) {
    DiaryTopAppBar(text = "Home", navigationIcon = {
        IconButton(onClick = onMenuClicked) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    },
        actions = {
            IconButton(onClick = onDateRangeClicked) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Range",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        })
}

@Composable
private fun HomeDrawerMenu(
    drawerState: DrawerState,
    onSignOutClick: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        content = content,
        drawerContent = {
            ModalDrawerSheet() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(250.dp),
                        painter = painterResource(id = com.merttoptas.diaryapp.R.drawable.logo),
                        contentDescription = "Logo"
                    )
                }
                NavigationDrawerItem(
                    label = {
                        Row(Modifier.padding(horizontal = 12.dp)) {
                            Image(
                                painter = painterResource(id = com.merttoptas.diaryapp.R.drawable.google_logo),
                                contentDescription = "Sign Out"
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Sign Out")

                        }
                    },
                    selected = false,
                    onClick = onSignOutClick
                )
            }
        },
    )
}