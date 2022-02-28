package com.rajaditya.fampayassignment.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.rajaditya.fampay.models.CardImage

@Composable
fun CardHC9(height: Float, ratio: Float, url: String, backgroundImage: CardImage) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .height(height = height.dp)
            .width((height * ratio).dp)
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
        Image(
            painter = rememberImagePainter(
                data = backgroundImage.imgUrl,
            ),
            contentDescription = "avatar",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}
