package com.example.littlelemon

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.littlelemon.ui.theme.LittleLemonColor


@Composable
fun LowerPanel(menuData: List<MenuItemRoom>) {

    var starters by remember {
        mutableStateOf(false)
    }

    var mains by remember {
        mutableStateOf(false)
    }

    var desserts by remember {
        mutableStateOf(false)
    }

    var drinks by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "ORDER FOR DELIVERY!",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        LazyRow(
            Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            item {
                if (starters) {
                    Button(
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.green),
                        onClick = { starters = !starters }) { Text(text = "Starters") }

                } else {
                    Button(
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow),
                        onClick = { starters = !starters }) { Text(text = "Starters") }
                }
            }
            item {
                if (mains) {
                    Button(
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.green),
                        onClick = { mains = !mains }) { Text(text = "Mains") }

                } else {
                    Button(
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow),
                        onClick = { mains = !mains }) { Text(text = "Mains") }
                }
            }
            item {
                if (desserts) {
                    Button(
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.green),
                        onClick = { desserts = !desserts }) { Text(text = "Desserts") }

                } else {
                    Button(
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow),
                        onClick = { desserts = !desserts }) { Text(text = "Desserts") }
                }
            }
            item {
                if (drinks) {
                    Button(
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.green),
                        onClick = { drinks = !drinks }) { Text(text = "Drinks") }

                } else {
                    Button(
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow),
                        onClick = { drinks = !drinks }) { Text(text = "Drinks") }
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            thickness = 1.dp, color = LittleLemonColor.yellow
        )
        val filterList = filterMenu(starters, mains, desserts, drinks, menuData)

        LazyColumn() {
            itemsIndexed(filterList) { _, dish ->
                MenuDish(dish)
            }
        }
    }


}

fun filterMenu(starters: Boolean, mains: Boolean, desserts: Boolean, drinks: Boolean, menuData: List<MenuItemRoom>): List<MenuItemRoom> {
    val filterList = emptyList<MenuItemRoom>().toMutableList()
    if (starters) filterList += menuData.filter{dish -> dish.category == "starters"}
    if (mains) filterList += menuData.filter{dish -> dish.category == "mains"}
    if (desserts) filterList += menuData.filter{dish -> dish.category == "desserts"}
    if (drinks) filterList += menuData.filter{dish -> dish.category == "drinks"}

    if (!starters && !mains && !desserts && !drinks) {
        return menuData
    }
    return filterList
}

@Composable
fun MenuDish(dish: MenuItemRoom) {

    Card() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = dish.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .padding(top = 5.dp, bottom = 5.dp)
                )
                Text(
                    text = dish.price,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            AsyncImage(
                model = dish.image,
                contentDescription = dish.description,
                modifier = Modifier
                    .clip(RoundedCornerShape(1.dp))
                    .padding(start = 5.dp, end = 5.dp),
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp, color = LittleLemonColor.yellow
    )
}