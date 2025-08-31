package com.tandroid.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tandroid.data.MuseumObject
import com.tandroid.data.MuseumRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(museumRepository: MuseumRepository) : ViewModel() {

    private val url = "https://qa.pilloo.ai/GeneratedPDF/Companies/202/2025-2026/DL.pdf"
    private val htmlText = "<h2>Welcome to KMP Notes</h2>\n" +
            "<p>This is a <b>sample note</b> with HTML and interactive elements.</p>\n" +
            "<button onclick=\"showInfo('Clicked on Button 1')\">Click Me 1</button>\n" +
            "<a href=\"#\" onclick=\"showInfo('Link Clicked')\">Click This Link</a>\n" +
            "<script>\n" +
            "function showInfo(msg) {\n" +
            "if (window.JavaScriptBridge) {\n" +
            "window.JavaScriptBridge.postMessage(msg);\n" +
            "}\n" +
            "}\n" +
            "</script>"

    val objects: StateFlow<List<MuseumObject>> =
        museumRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getPreviewWebUrl() = url

    fun getHTMLText() = htmlText

}
