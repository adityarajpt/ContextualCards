package com.rajaditya.fampayassignment.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.rajaditya.fampay.models.CardImage

@Composable
fun CardHC5(
    height: Float,
    title: String,
    description: String,
    backgroundImage: CardImage,
    textColor: Color,
    url: String,
) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .width(configuration.screenWidthDp.dp - 40.dp)
            .height(height = height.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                )
            },
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = backgroundImage.imgUrl,
                ),
                contentDescription = "avatar",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(text = title, color = textColor, fontSize = 21.sp)
                Text(text = description, color = textColor, fontSize = 15.sp)
            }
        }
    }
}