package com.tandroid.screens.webScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import quick_todo_app.composeapp.generated.resources.Res
import quick_todo_app.composeapp.generated.resources.back

@Composable
fun WebScreen(
    webURL: String?,
    html: String?,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(Res.string.back))
                    }
                }
            )
        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
    ) { paddingValues ->

        Column(
            Modifier
                .padding(paddingValues)
        ) {
        WebViewContainer(webURL,html)}
    }
}
