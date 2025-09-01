package com.tandroid

import android.app.Application
import com.tandroid.di.initKoin
import com.tandroid.data.DatabaseDriverFactory

class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(DatabaseDriverFactory(this))

    }
}
