package org.zwsmith.myapplication.core.dependencyInjection

import android.app.Application

import org.zwsmith.myapplication.MyApplication

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun provideApplication(): MyApplication {
        return application
    }
}
