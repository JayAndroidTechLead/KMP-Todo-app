package com.tandroid.screens.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tandroid.data.Note
import com.tandroid.screens.EmptyScreenContent
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import quick_todo_app.composeapp.generated.resources.Res
import quick_todo_app.composeapp.generated.resources.add_todo
import quick_todo_app.composeapp.generated.resources.home
import quick_todo_app.composeapp.generated.resources.open_web_link
import quick_todo_app.composeapp.generated.resources.view_html
import kotlin.time.ExperimentalTime
import kotlin.time.Instant.Companion.fromEpochMilliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigateToWebScreen: (url: String?,html: String?) -> Unit,
    navigateToDetails: (objectId: Long) -> Unit
) {
    val viewModel = koinViewModel<MainViewModel>()
    val objects by viewModel.objects.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars), topBar = {
            TopAppBar(title = { Text(stringResource(Res.string.home)) })
        },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.padding(10.dp), onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = stringResource(Res.string.add_todo))
            }
        }
    ) { paddingValues ->

        Column(
            Modifier
                .padding(paddingValues)
        ) {

            // Row of buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button({
                    navigateToWebScreen(viewModel.getPreviewWebUrl(), null)
                }, Modifier.padding(start = 8.dp), content = {
                    Text(stringResource(Res.string.open_web_link))
                })
                Button(onClick = {
                    navigateToWebScreen(null, viewModel.getHTMLText())
                }) {
                    Text(stringResource(Res.string.view_html))
                }
            }
            AnimatedContent(objects.isNotEmpty()) { objectsAvailable ->
                if (objectsAvailable) {
                    ObjectGrid(
                        objects = objects,
                        onObjectClick = navigateToDetails,
                    )
                } else {
                    EmptyScreenContent(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
private fun ObjectGrid(
    objects: List<Note>,
    onObjectClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier.fillMaxSize()
    ) {
        items(objects) { obj ->
            ObjectFrame(
                note = obj,
                onClick = { onObjectClick(obj.id) },
                onDelete = { onObjectClick(obj.id) },
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
private fun ObjectFrame(
    note: Note,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(note.title, style = MaterialTheme.typography.titleLarge)

            val instant = fromEpochMilliseconds(note.createdAt)

            val dateTime: LocalDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

            val date: LocalDate = dateTime.date
            Text("Created: $date", style = MaterialTheme.typography.bodySmall)

            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}
