package com.example.myapplication.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen() {
    val shape = RoundedCornerShape(topLeft = 50f, topRight = 50f, bottomLeft = 50f, bottomRight = 50f)
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth(1f)
            .preferredHeight(350.dp)

    ) {
        Text(
            text = "data Card",
            modifier = Modifier.padding(8.dp)
        )

    }

}