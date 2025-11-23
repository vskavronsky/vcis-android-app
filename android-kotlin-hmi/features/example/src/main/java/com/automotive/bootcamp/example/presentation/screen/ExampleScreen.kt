package com.automotive.bootcamp.example.presentation.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.automotive.bootcamp.example.presentation.state.ExampleUIState
import compose_theme.theme.IntelliAutoHMITheme

@Composable
fun ExampleScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExampleViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    BackHandler {
        onClose()
    }

    LaunchedEffect(Unit) {
        Toast.makeText(context, "Hello there", Toast.LENGTH_LONG).show()
    }

    ExampleView(
        state = state,
        onExampleClick = {
            viewModel.exampleClick()
        },
        onExampleSuspendClick = {
            viewModel.exampleSuspendClick()
        },
        modifier = modifier,
    )
}

@Composable
private fun ExampleView(
    state: ExampleUIState,
    onExampleClick: () -> Unit,
    onExampleSuspendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Example flow: ${state.example}",
            )
            Text(
                text = "Suspend click result: ${state.result}",
            )
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(
                    onClick = onExampleClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text("Click")
                }
                Button(
                    onClick = onExampleSuspendClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text("Suspend click")
                }
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            DataView(state = state)
        }
    }
}

@Composable
fun DataView(state: ExampleUIState) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Users:", modifier = Modifier.padding(bottom = 4.dp))
        }
        items(state.users) { user ->
            Text(text = "- ${user.id}: ${user.name}")
        }
        item {
            Text(text = "Warnings:", modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))
        }
        items(state.warnigs) { warning ->
            Text(text = "- ${warning.message}: ${warning.code}")
        }
        item {
            Text(text = "TPMS Info:", modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))
        }
        items(state.tpms) { tpms ->
            Text(text = "- ${tpms.tire} : ${tpms.pressure}")
        }
    }
}

@Preview
@Composable
private fun DefaultExampleScreenPreview() {
    IntelliAutoHMITheme {
        Surface {
            ExampleView(
                state = ExampleUIState(
                    "empty", "suspend",
                    temp = TODO(),
                    users = TODO(),
                    warnigs = TODO(),
                    tpms = TODO()
                ),
                onExampleClick = {},
                onExampleSuspendClick = {},
            )
        }
    }
}