package com.github.rodrigohenriques.picnic;

import android.app.Application;

import com.github.rodrigohenriques.data.internal.di.DataModule;
import com.github.rodrigohenriques.picnic.di.AppComponent;
import com.github.rodrigohenriques.picnic.di.AppModule;
import com.github.rodrigohenriques.picnic.di.DaggerAppComponent;

public class AndroidApplication extends Application {
    private AppComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public AppComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
