package com.github.rodrigohenriques.picnic.di;

import android.app.Application;

import com.github.rodrigohenriques.domain.interactor.UiThreadExecutor;
import com.github.rodrigohenriques.picnic.ui.AndroidUiThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton public Application provideContext() {
        return application;
    }

    @Provides @Singleton public UiThreadExecutor provideUiThreadExecutor() {
        return new AndroidUiThreadExecutor();
    }
}
