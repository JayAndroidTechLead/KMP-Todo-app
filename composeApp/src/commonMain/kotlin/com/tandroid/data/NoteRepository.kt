package com.tandroid.data

import com.tandroid.sqldelight.NoteDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NoteRepository(
    private val db: NoteDatabase
) {
    fun getObjects(): Flow<List<Note>> = flow { emit(db.notesQueries.selectAll().executeAsList()) }
        .map { list ->
            list.map { note ->
                Note(note.id, note.title, note.body, note.createdAt)
            }
        }

    fun getObjectById(objectId: Long): Flow<Note?> =
        flow { emit(db.notesQueries.findNoteById(objectId).executeAsOne()) }
            .map { data ->
                Note(
                    data.id,
                    data.title,
                    data.body, data.createdAt
                )
            }

    fun addNote(title: String, body: String, createdAt: Long) {
        db.notesQueries.insertNote(title, body, createdAt)
    }

    fun deleteNote(id: Long) {
        db.notesQueries.deleteNote(id)
    }
}
