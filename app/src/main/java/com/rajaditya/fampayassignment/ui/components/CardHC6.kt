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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.rajaditya.fampay.models.CardImage
import com.rajaditya.fampayassignment.R

@Composable
fun CardHC6(
    title: String,
    cardImage: CardImage,
    url: String?
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp, 15.dp, 20.dp, 15.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberImagePainter(
                    data = cardImage.imgUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .weight(1f, true),
                fontSize = 14.sp,
            )
            Image(
                painter = painterResource(R.drawable.ic_arrow),
                contentDescription = "avatar",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(10.dp)
                    .height(16.dp)
            )
        }
    }
}