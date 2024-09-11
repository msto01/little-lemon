package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Profile(navController: NavHostController, sharedPreferences: SharedPreferences) {

    val scrollState = rememberScrollState()



    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
            TopAppBarProfile(navController)

            HorizontalDivider(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                thickness = 1.dp, color = LittleLemonColor.green
            )

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 50.dp, bottom = 50.dp, start = 20.dp)
        ) {
            Text(
                text = "Personal information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
            OutlinedTextField(
                value = sharedPreferences.getString("firstName", "") ?: "",
                enabled = false,
                label = { Text(text = "First name") },
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 25.dp)
            )

            OutlinedTextField(
                value = sharedPreferences.getString("lastName", "") ?: "",
                enabled = false,
                label = { Text(text = "Last name") },
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 25.dp)
                )

            OutlinedTextField(
                value = sharedPreferences.getString("email", "") ?: "",
                enabled = false,
                label = { Text(text = "Email") },
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 140.dp)
            )

        Column(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally

            )   {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { logOut(sharedPreferences, navController) }
            ) {
                Text(text = "Log out")
            }
        }
    }
}

fun logOut(sharedPreferences: SharedPreferences, navController: NavHostController) {
    sharedPreferences.edit()
        .remove("firstName")
        .remove("lastName")
        .remove("email")
        .apply()
    navController.navigate("Onboarding")
}