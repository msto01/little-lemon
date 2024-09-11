package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun TopAppBarProfile(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Image(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = "Menu Icon",
                modifier = Modifier.size(36.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier.fillMaxWidth(0.5F)
                .padding(horizontal = 36.dp)
        )
        IconButton(onClick = { navController.navigate("Home")}) {
            Image(
                painter = painterResource(id = R.drawable.baseline_home_24),
                contentDescription = "Profile Photo",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}
