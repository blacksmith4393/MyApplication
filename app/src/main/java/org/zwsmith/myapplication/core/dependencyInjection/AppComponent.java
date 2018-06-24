package org.zwsmith.myapplication.core.dependencyInjection;

import org.zwsmith.myapplication.MyApplication;
import org.zwsmith.myapplication.presentation.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    MyApplication getApplication();
    MainViewModel getMainViewModel();
}

