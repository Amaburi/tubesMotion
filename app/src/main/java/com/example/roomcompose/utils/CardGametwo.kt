package com.example.roomcompose.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomcompose.Object.Games
import com.example.roomcompose.R

@Composable
fun CardGametwo(
    game: Games,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .width(320.dp)
                .height(130.dp)
                .testTag("game_card") ,
            shape = RoundedCornerShape(22.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hogwarts),
                    contentDescription = game.name,
                    modifier = Modifier
                        .size(109.dp)
                        .height(50.dp)
                        .padding(start = 8.dp, top = 20.dp)
                )

                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        game.name,
                        fontSize = 21.sp
                    )
                    Text(
                        "$${game.price}",
                        fontSize = 19.sp
                    )
                }

                OutlinedButton(
                    onClick = { /* TODO: Handle Buy */ },
                    modifier = Modifier.padding(top = 45.dp)
                ) {
                    Text("Buy")
                }
            }
        }

        // Left and Right Navigation Buttons
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = onLeftClick) {
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow), // Replace with your left arrow icon
                    contentDescription = "Previous",
                    tint = if (isDarkTheme)Color.White else colorResource(id = R.color.black)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = onRightClick) {
                Icon(
                    painter = painterResource(id = R.drawable.right_arrow), // Replace with your right arrow icon
                    contentDescription = "Next",
                    tint = if (isDarkTheme)Color.White else colorResource(id = R.color.black)
                )
            }
        }
    }
}
