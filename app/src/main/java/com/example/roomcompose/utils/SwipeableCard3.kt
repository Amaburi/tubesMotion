package com.example.roomcompose.utils
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomcompose.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwipeableGameCards3() {
    val pagerState = rememberPagerState()

    HorizontalPager(count = upcomingGames.size, state = pagerState) { page ->
        val game = upcomingGames[page]
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = game.imageRes),
                    contentDescription = game.title,
                    modifier = Modifier.size(150.dp)
                )
                Column(
                    modifier =  Modifier.fillMaxWidth()
                ){
                    Text(
                        text = game.title,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = game.releaseDate,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = game.description,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
