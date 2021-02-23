package com.example.myapplication.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.domain.models.Recipe
import com.example.myapplication.domain.utils.TitleText


@Composable
fun RecipeCard(
    recipe:Recipe,
    onClick: () ->Unit, // to click what happens to the card

){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(
            bottom = 6.dp,
            top = 6.dp
        )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,


    ) {

        Column {
            recipe.featuredImage?.let { url ->
                Image(
                    bitmap = imageResource(R.drawable.empty_plate),
                    "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .preferredHeight(225.dp),
                    contentScale = ContentScale.Crop
                )
            }
            recipe.title?.let {
                TitleText("Title of the recipe")
            }
            IconButton(onClick= onClick){
                Icon(
                    vectorResource(R.drawable.add_24),
                    "",
                    tint = Color(0xffe9e9e9e)

                )
        }
        }
    }
}