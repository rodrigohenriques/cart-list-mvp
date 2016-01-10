package com.github.rodrigohenriques.picnic.di;

import com.github.rodrigohenriques.data.internal.di.DataModule;
import com.github.rodrigohenriques.picnic.ui.activities.BaseActivity;
import com.github.rodrigohenriques.picnic.ui.activities.ProductsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = { DataModule.class, AppModule.class } )
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(ProductsActivity productsActivity);
}
