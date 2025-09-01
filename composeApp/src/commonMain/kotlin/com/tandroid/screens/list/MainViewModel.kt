package com.tandroid.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tandroid.data.Note
import com.tandroid.data.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class MainViewModel(noteRepository: NoteRepository) : ViewModel() {

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

    private val _objects =  MutableStateFlow<List<Note>>(emptyList())

    init {
        viewModelScope.launch {
            for (i in 1..5){
                noteRepository.addNote("New Note $i", "New Note body $i", Clock.System.now().toEpochMilliseconds())
            }
            noteRepository.getObjects().collect {
                _objects.value = it
            }
        }
    }

    val objects: StateFlow<List<Note>> = _objects.asStateFlow()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getPreviewWebUrl() = url

    fun getHTMLText() = htmlText

}
