package com.example.roomcompose.utils

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginText(navController: NavController, name : String, name2 : String, destination : String) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White
            )
        ){
            append(name2)
        }

        withStyle(
            style = SpanStyle(
                color = Color(0xFFE2F163),
                fontSize = 16.sp,
            )
        ) {
            pushStringAnnotation(tag = name, annotation = name)
            append(name)
            pop()
        }
    }

    ClickableText(
        text = text,
        onClick = { offset ->
            text.getStringAnnotations(tag = name, start = offset, end = offset)
                .firstOrNull()?.let {
                    navController.navigate(destination)
                }
        }
    )
}
