package org.zwsmith.myapplication

import android.app.Application

import org.zwsmith.myapplication.core.dependencyInjection.AppComponent
import org.zwsmith.myapplication.core.dependencyInjection.AppModule
import org.zwsmith.myapplication.core.dependencyInjection.DaggerAppComponent

class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
