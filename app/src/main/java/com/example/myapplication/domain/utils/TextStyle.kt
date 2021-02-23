package com.example.myapplication.domain.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(title: String){
    return Text(
        text =  title,
        style = TextStyle(
            color = Color(0xff686868),
            fontSize = 14.sp
        ),
        modifier =
        Modifier
            .padding(8.dp)
    )
}

@Composable
fun ContentText(content:String){
    return Text(
        text =  content,
        style = TextStyle(
            color = Color(0xff949494),
            fontSize = 12.sp
        ),
        modifier = Modifier
            .padding(8.dp)
    )
}