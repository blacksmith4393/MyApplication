package org.zwsmith.myapplication;

import android.app.Application;
import android.content.Context;

import org.zwsmith.myapplication.core.dependencyInjection.AppComponent;
import org.zwsmith.myapplication.core.dependencyInjection.AppModule;
import org.zwsmith.myapplication.core.dependencyInjection.DaggerAppComponent;

public class MyApplication extends Application {
    private AppComponent appComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
