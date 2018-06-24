package org.zwsmith.myapplication.core.dependencyInjection;

import android.app.Application;

import org.zwsmith.myapplication.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public MyApplication provideApplication() {
        return application;
    }
}
