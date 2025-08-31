package com.tandroid.screens.detail

import androidx.lifecycle.ViewModel
import com.tandroid.data.MuseumObject
import com.tandroid.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
