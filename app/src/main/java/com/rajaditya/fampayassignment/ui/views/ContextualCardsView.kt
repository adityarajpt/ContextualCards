package com.rajaditya.fampayassignment.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import com.rajaditya.fampay.models.CardGroup
import com.rajaditya.fampay.models.CardImage
import com.rajaditya.fampay.models.DesignType
import com.rajaditya.fampayassignment.ui.components.*
import com.rajaditya.fampayassignment.utils.toCardImageorEmpty
import com.rajaditya.fampayassignment.utils.toHextoJetpackColorOrBlack
import com.rajaditya.fampayassignment.utils.toHextoJetpackColorOrWhite
import com.rajaditya.fampayassignment.utils.toStringOrEmpty

class ContextualCardsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private val _cardGroupData = mutableStateOf<List<CardGroup>>(emptyList())
    private val _cardsDisabled = mutableStateOf<List<Int>>(emptyList())

    lateinit var disableCardAction: (Int) -> Unit
    lateinit var permanentDisableAction: (Int) -> Unit

    var cardGroupData: List<CardGroup>
        get() = _cardGroupData.value
        set(value) {
            _cardGroupData.value = value
        }
    var cardsDisabled: List<Int>
        get() = _cardsDisabled.value
        set(value) {
            _cardsDisabled.value = value
        }


    @ExperimentalAnimationApi
    @Composable
    override fun Content() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            cardGroupData.onEach { cardGroup ->
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                        .fillMaxWidth()
                )
                if (cardGroup.scrollable) {
                    LazyRow {
                        items(cardGroup.cardList) { card ->
                            when (cardGroup.designType) {
                                DesignType.SMALL_DISPLAY_CARD -> {
                                    CardHC1(
                                        card.title.toStringOrEmpty(),
                                        card.description.toStringOrEmpty(),
                                        card.icon.toCardImageorEmpty(),
                                        card.bgColor.toHextoJetpackColorOrWhite(),
                                        card.url.orEmpty(),
                                    )
                                }
                                DesignType.SMALL_CARD_WITH_ARROW -> {
                                    CardHC6(
                                        card.title.toStringOrEmpty(),
                                        card.icon.toCardImageorEmpty(),
                                        card.url,
                                    )
                                }
                                DesignType.IMAGE_CARD -> {
                                    CardHC5(
                                        height = cardGroup.height?.toFloat() ?: 150f,
                                        title = card.title.toStringOrEmpty(),
                                        description = card.description.toStringOrEmpty(),
                                        backgroundImage = card.bgImage.toCardImageorEmpty(),
                                        textColor = card.bgColor.toHextoJetpackColorOrBlack(),
                                        url = card.url.toStringOrEmpty(),
                                    )
                                }
                                DesignType.DYNAMIC_WIDTH_CARD -> {
                                    CardHC9(
                                        cardGroup.height?.toFloat() ?: 100f,
                                        card.bgImage?.aspectRatio ?: 1f,
                                        card.url.orEmpty(),
                                        card.bgImage.toCardImageorEmpty(),
                                    )
                                }
                                DesignType.BIG_DISPLAY_CARD -> {
                                    if (cardGroup.id !in cardsDisabled) {
                                        CardHC3(
                                            card.title.toStringOrEmpty(),
                                            card.description.toStringOrEmpty(),
                                            cardGroup.id,
                                            card.bgImage.toCardImageorEmpty(),
                                            card.ctaList.orEmpty(),
                                            disableCardAction,
                                            permanentDisableAction,
                                        )
                                    }
                                }
                            }
                            Spacer(Modifier.width(10.dp))
                        }
                    }
                } else {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        cardGroup.cardList.onEachIndexed { index, card ->
                            Box(modifier = Modifier.weight(1f)) {
                                when (cardGroup.designType) {
                                    DesignType.SMALL_DISPLAY_CARD -> {
                                        CardHC1(
                                            card.title.toStringOrEmpty(),
                                            card.description.toStringOrEmpty(),
                                            card.icon.toCardImageorEmpty(),
                                            card.bgColor.toHextoJetpackColorOrWhite(),
                                            card.url.orEmpty()
                                        )
                                    }
                                    DesignType.SMALL_CARD_WITH_ARROW -> {
                                        CardHC6(
                                            card.title.toStringOrEmpty(),
                                            card.icon ?: CardImage("", ""),
                                            card.url,
                                        )
                                    }
                                    DesignType.IMAGE_CARD -> {
                                        CardHC5(
                                            height = cardGroup.height?.toFloat() ?: 150f,
                                            title = card.title.toStringOrEmpty(),
                                            description = card.description.toStringOrEmpty(),
                                            backgroundImage = card.bgImage.toCardImageorEmpty(),
                                            textColor = card.bgColor.toHextoJetpackColorOrBlack(),
                                            url = card.url.toStringOrEmpty(),
                                        )
                                    }
                                    DesignType.DYNAMIC_WIDTH_CARD -> {
                                        CardHC9(
                                            cardGroup.height?.toFloat() ?: 100f,
                                            card.bgImage?.aspectRatio ?: 1f,
                                            card.url.orEmpty(),
                                            card.bgImage.toCardImageorEmpty(),
                                        )
                                    }
                                    DesignType.BIG_DISPLAY_CARD -> {
                                        if (cardGroup.id !in cardsDisabled) {
                                            CardHC3(
                                                card.title.toStringOrEmpty(),
                                                card.description.toStringOrEmpty(),
                                                cardGroup.id,
                                                card.bgImage.toCardImageorEmpty(),
                                                card.ctaList.orEmpty(),
                                                disableCardAction,
                                                permanentDisableAction,
                                            )
                                        }
                                    }
                                }
                            }
                            if (index != cardGroup.cardList.size - 1) {
                                Spacer(Modifier.width(10.dp))
                            }
                        }
                    }
                }
            }
        }
    }

}
