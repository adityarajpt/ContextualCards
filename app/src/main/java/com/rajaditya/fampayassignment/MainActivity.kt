package com.rajaditya.fampayassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.preference.PreferenceManager
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.rajaditya.fampayassignment.MainActivityViewModel.UiState
import com.rajaditya.fampayassignment.ui.theme.FampayAssignmentTheme
import com.rajaditya.fampayassignment.ui.theme.activityBackgroundColor
import com.rajaditya.fampayassignment.ui.views.ContextualCardsView
import com.rajaditya.fampayassignment.ui.views.ShimmerAnimation

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = MainActivityViewModel.MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))
        viewModel = ViewModelProvider(this, viewModelFactory).get()
        setContent {
            FampayAssignmentTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainActivityViewModel) {
    val state = viewModel.uiState.collectAsState()
    val cardsDisabled = viewModel.cardsDisabled.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.value == UiState.Loading),
                onRefresh = { viewModel.fetchContextualCards() },
            ) {
                Cards(
                    state.value,
                    cardsDisabled.value,
                    viewModel::fetchContextualCards,
                    viewModel::temporarilyDisableCard,
                    viewModel::permanentlyDisableCard
                )
            }
        },
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                content = {
                    Image(
                        painterResource(R.drawable.ic_fampaylogo),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 15.dp)
                            .fillMaxSize(),
                    )
                },
            )
        }
    )
}

@Composable
fun Cards(
    state: UiState,
    disabledCards: List<Int>,
    refreshAction: () -> Unit,
    disableCardsAction: (Int) -> Unit,
    permanentCardDisableAction : (Int) -> Unit,
) {
    when (state) {
        is UiState.Loading ->
            LazyColumn(Modifier.fillMaxSize()) {
                repeat(5) {
                    item {
                        ShimmerAnimation()
                    }
                }
            }
        is UiState.Error -> {
            Surface(modifier = Modifier.fillMaxSize()) {
                Button(
                    onClick = { refreshAction() },
                    modifier = Modifier.wrapContentSize(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = activityBackgroundColor),
                ) {
                    Text(stringResource(R.string.error_message))
                }
            }
        }
        is UiState.Loaded -> {
            Column(
                Modifier.verticalScroll(rememberScrollState()),
            ) {
                AndroidView(
                    factory = { context ->
                        ContextualCardsView(context).apply {
                            cardGroupData = state.response.cardGroup
                            cardsDisabled = disabledCards
                            disableCardAction = disableCardsAction
                            permanentDisableAction = permanentCardDisableAction
                        }
                    },
                    update = {
                        it.cardGroupData = state.response.cardGroup
                        it.cardsDisabled = disabledCards
                    }
                )
            }
        }
    }
}