package com.tandroid.di

import com.tandroid.data.DatabaseDriverFactory
import com.tandroid.data.NoteRepository
import com.tandroid.data.createDatabase
import com.tandroid.screens.list.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single {

        NoteRepository(get()).apply {
        }
    }
}

fun databaseModule(driverFactory: DatabaseDriverFactory) = module {
    single { createDatabase(driverFactory) } // returns NoteDatabase
}

val viewModelModule = module {
    factoryOf(::MainViewModel)
}

fun initKoin(driverFactory: DatabaseDriverFactory) {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
            databaseModule(driverFactory = driverFactory) ,
        )
    }
}
