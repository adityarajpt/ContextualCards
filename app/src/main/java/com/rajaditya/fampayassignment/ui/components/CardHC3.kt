package com.rajaditya.fampayassignment.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.rajaditya.fampay.models.CardImage
import com.rajaditya.fampay.models.Cta
import com.rajaditya.fampayassignment.R
import com.rajaditya.fampayassignment.utils.toHextoJetpackColorOrBlack

@ExperimentalAnimationApi
@Composable
fun CardHC3(
    title: String,
    description: String,
    cardId: Int,
    backgroundImage: CardImage,
    ctaList: List<Cta> = emptyList(),
    disableCardAction: (Int) -> Unit,
    permanentDisableAction: (Int) -> Unit,
) {
    var isVisible by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(350.dp)
            .width(configuration.screenWidthDp.dp - 40.dp + 114.dp)
            .horizontalScroll(rememberScrollState()),
    ) {
        AnimatedVisibility(visible = isVisible) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(114.dp)
                    .padding(start = 21.dp, end = 21.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(59.dp)
                        .background(Color(0xFFF7F6F3))
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    disableCardAction(cardId)
                                }
                            )
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_bell),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(20.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text("remind later", color = Color.Black, fontSize = 10.sp)
                }
                Spacer(Modifier.height(37.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(59.dp)
                        .background(Color(0xFFF7F6F3))
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    disableCardAction(cardId)
                                    permanentDisableAction(cardId)
                                }
                            )
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_cross),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(20.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text("dismiss now", color = Color.Black, fontSize = 10.sp)
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .width(configuration.screenWidthDp.dp - 40.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            if (isVisible) {
                                isVisible = false
                            }
                        },
                        onLongPress = {
                            isVisible = !isVisible
                        }
                    )
                },
            backgroundColor = Color(0xFF454AA6),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box() {
                Image(
                    painter = rememberImagePainter(
                        data = backgroundImage.imgUrl,
                    ),
                    contentDescription = "avatar",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .padding(33.dp, 28.dp, 27.dp, 20.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.height(80.dp))
                    Text(
                        text = title,
                        fontSize = 30.sp,
                    )
                    Text(
                        text = description,
                        fontSize = 12.sp,
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ctaList.first().bgColor.toHextoJetpackColorOrBlack()
                        ),
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ctaList.first().url))
                            context.startActivity(intent)
                        },
                    ) {
                        Text(
                            ctaList.first().text,
                            color = ctaList.first().textColor.toHextoJetpackColorOrBlack(),
                        )
                    }
                }
            }
        }
    }
}