package com.tandroid.screens.webScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WebViewContainer(url: String?, html: String?) {
    UIKitView(factory = {
            val webView = WKWebView(frame = CGRectMake(0.0, 0.0, 300.0, 500.0), configuration = WKWebViewConfiguration())
            when {
                url != null -> {
                    val nsUrl = NSURL.URLWithString(url)
                    val request = NSURLRequest.requestWithURL(nsUrl!!)
                    webView.loadRequest(request)
                }
                html != null -> webView.loadHTMLString(html, baseURL = null)
            }
            webView
        }, modifier = Modifier.fillMaxSize().background(Color.Black), update = {  }, onRelease = {  }, properties = UIKitInteropProperties(isInteractive = true, isNativeAccessibilityEnabled = true))
}