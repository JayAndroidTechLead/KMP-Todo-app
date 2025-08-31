package com.tandroid.screens.webScreen

import androidx.compose.runtime.Composable


@Composable
expect fun WebViewContainer(url: String? = null, html: String? = null)