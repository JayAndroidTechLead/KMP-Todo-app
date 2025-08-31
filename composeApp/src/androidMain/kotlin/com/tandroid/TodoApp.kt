package com.tandroid

import android.app.Application
import com.tandroid.di.initKoin

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
