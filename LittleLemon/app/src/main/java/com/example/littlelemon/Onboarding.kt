package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Onboarding(navController: NavHostController, sharedPreferences: SharedPreferences) {

    val scrollState = rememberScrollState()

    var firstName by rememberSaveable {
        mutableStateOf("")
    }

    var lastName by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager
                        .clearFocus()
                })

            }
    ) {
        val context = LocalContext.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(
                Modifier.padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.little_lemon_logo),
                    contentDescription = "Little Lemon logo",
                    modifier = Modifier.size(width = 200.dp, height = 80.dp)
                )
            }

            Row(
                Modifier
                    .background(color = LittleLemonColor.green)
                    .fillMaxWidth()
                    .padding(top = 45.dp, bottom = 45.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Let's get to know you",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                )
            }
        }
            Text(
                modifier = Modifier.padding(top = 50.dp, bottom = 50.dp, start = 20.dp),
                text = "Personal information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            OutlinedTextField(
                value = firstName,
                label = { Text(text = "First name") },
                onValueChange = { firstName = it },
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, bottom = 25.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, bottom = 25.dp),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Last name") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, bottom = 70.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus(true) }
                )
            )

        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.Center,

        )   {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { formVerifier(firstName, lastName, email, sharedPreferences, navController, context) }
            ) {
                Text(text = "Register")
              }
        }
    }
}

fun formVerifier(firstName: String, lastName: String, email: String, sharedPreferences: SharedPreferences, navController: NavHostController, context: Context) {
    if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
        sharedPreferences.edit()
            .putString("firstName", firstName)
            .putString("lastName", lastName)
            .putString("email", email)
            .apply()
        val text = "Registration successful."
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(context, text, duration)
        toast.show()
        navController.navigate("Home")
    } else {
        val text = "Registration unsuccessful. Please enter all data."
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }
}