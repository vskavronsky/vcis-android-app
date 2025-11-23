package com.automotive.bootcamp.adas.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.presentation.components.AdasCard
import com.automotive.bootcamp.adas.presentation.components.AdasTile
import com.automotive.bootcamp.adas.presentation.state.AdasFeatureState

@Composable
fun AdasScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AdasViewModel = hiltViewModel()
) {
    val features = viewModel.features.collectAsStateWithLifecycle()

    BackHandler {
        onClose()
    }

    AdasView(
        modifier = modifier,
        features = features,
        onLoadSettings = {
            viewModel.loadFeatureSettings(it)
        }
    )
}

@Composable
private fun AdasView(
    features: State<List<AdasFeatureState>>,
    onLoadSettings: (type: AdasFeatureType) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xfff9fafb))
    ) {
        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally)
        ){
            items(features.value) {
                AdasTile(
                    config = it.config,
                    tileData = it.tileConfig
                )
            }
        }

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically)
        ) {
            items(features.value) {
                LaunchedEffect(it.type) {
                    onLoadSettings(it.type)
                }

                AdasCard(
                    config = it.config,
                    cardData = it.cardConfig,
                    onFeatureToggle = {
                        it.onToggle()
                    },
                    onSetOperationMode = {mode ->
                        it.onSelectMode(mode)
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_TABLET,
    backgroundColor = 0x6264,
)
@Composable
fun AdasViewPreview() {
//    AdasView(state = AdasViewState.Empty)
}
