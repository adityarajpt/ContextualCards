package com.rajaditya.fampayassignment.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.rajaditya.fampay.models.CardImage

@Composable
fun CardHC1(
    title: String,
    description: String,
    cardImage: CardImage,
    backgroundColor: Color,
    url: String,
) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .width(configuration.screenWidthDp.dp - 40.dp)
            .height(60.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                )
            },
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp, 12.dp, 15.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = cardImage.imgUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .clip(CircleShape)                       // clip to the circle shape
            )
            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

    }
}