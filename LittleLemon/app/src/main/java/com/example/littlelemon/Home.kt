package com.example.littlelemon

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController


@Composable
fun Home (navController: NavController, menuItems: List<MenuItemRoom>) {

    val focusManager = LocalFocusManager.current

    Column (
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager
                        .clearFocus()
                })}

    ) {
        TopAppBar(navController)
        UpperPanel(menuItems)
    }
}





