package com.tandroid.screens.webScreen

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun WebViewContainer(url: String?, html: String?) {
    AndroidView(factory = { context: Context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = false

            webViewClient = WebViewClient() // handles navigation inside WebView
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    // You can show a progress bar if needed
                    println("Loading progress: $newProgress%")
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    println("Page title: $title")
                }

            }

            when {
                url != null -> {loadUrl("https://docs.google.com/gview?embedded=true&url=$url") }
                html != null -> loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
            }
        }
    },
        modifier = Modifier.fillMaxSize())
}