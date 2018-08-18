package org.zwsmith.myapplication

import android.app.Application
import android.content.Context

import org.zwsmith.myapplication.core.dependencyInjection.AppComponent
import org.zwsmith.myapplication.core.dependencyInjection.AppModule
import org.zwsmith.myapplication.core.dependencyInjection.DaggerAppComponent

class MyApplication : Application() {
    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
