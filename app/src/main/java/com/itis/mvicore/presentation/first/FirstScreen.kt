package com.itis.mvicore.presentation.first

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.itis.mvicore.di.appModule
import com.itis.mvicore.domain.first.mvi.FirstNews
import com.itis.mvicore.presentation.first.mvi.Actions
import com.itis.mvicore.presentation.first.mvi.FirstScreenUiState
import com.itis.newsviewer.domain.news.model.NewsInfo
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstScreen(viewModel: FirstScreenViewModel = koinViewModel()) {
    val state by viewModel.getStateObserver().observeAsState(FirstScreenUiState())
    val actions by viewModel.news.observeAsState(initial = null)
    val snackbar = remember {
        SnackbarHostState()
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbar) }
    ) {
        FirstScreenContent(state, onNewsItemClick = viewModel::onNewsItemClick, viewModel::onReloadClick)
    }
    FirstScreenActions(
        viewAction = actions,
        snackbarHostState = snackbar
    )
}

@Composable
fun FirstScreenContent(
    viewState: FirstScreenUiState,
    onNewsItemClick: (NewsInfo) -> Unit,
    onReloadClick: () -> Unit,
) {
    Column {
        if (viewState.isLoading) {
            ProgressBar(Modifier)
        } else {
            Button(onClick = { onReloadClick()}) {
                Text(text = "Обновить")
            }
            LazyColumnSample(
                viewState,
                onNewsItemClick
            )
        }
    }
}

@Composable
fun LazyColumnSample(
    viewState: FirstScreenUiState,
    onNewsItemClick: (NewsInfo) -> Unit
) {
    val news = viewState.news
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
    ) {
        items(
            count = news.size,
            key = { i -> news[i].uuid },
        ) { index ->
            val item = news[index]
            MyListItem(newsInfo = item) { item ->
                onNewsItemClick(item)
            }
        }
    }
}

@Composable
private fun ProgressBar(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(color = Color.Yellow)
    }
}

@Composable
private fun ErrorMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
//        Text(
//            text = stringResource(R.string.error),
//            style = NewsViewerTheme.typography.body,
//            color = NewsViewerTheme.colors.errorColor
//        )
    }
}

@Composable
private fun MyListItem(
    newsInfo: NewsInfo,
    onClick: (NewsInfo) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(newsInfo)
            }
            .padding(8.dp)
            .background(
                color = Color.DarkGray,
                shape = RectangleShape
            )
            .padding(16.dp)
    ) {
        Text(
            text = newsInfo.title,
            color = Color.White
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        ) {
            AsyncImage(
                model = newsInfo.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()

            )
        }
    }
}

@Composable
private fun FirstScreenActions(
    viewAction: Actions?,
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(key1 = viewAction) {
        when (viewAction) {
            null -> Unit
            is Actions.ShowDialog -> snackbarHostState.showSnackbar(viewAction.message)
        }
    }
}
