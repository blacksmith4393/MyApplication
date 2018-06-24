package org.zwsmith.myapplication.core.dependencyInjection;

import android.app.Application;

import org.zwsmith.myapplication.presentation.MainFragment;
import org.zwsmith.myapplication.presentation.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    Application getApplication();
    MainViewModel getMainViewModel();
}

