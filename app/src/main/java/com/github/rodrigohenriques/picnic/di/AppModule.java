package com.github.rodrigohenriques.picnic.di;

import android.app.Application;

import com.github.rodrigohenriques.domain.interactor.GetCartListUseCase;
import com.github.rodrigohenriques.domain.interactor.GetCartListUseCaseImpl;
import com.github.rodrigohenriques.domain.interactor.UiThreadExecutor;
import com.github.rodrigohenriques.picnic.presenter.CartListPresenter;
import com.github.rodrigohenriques.picnic.presenter.CartListPresenterImpl;
import com.github.rodrigohenriques.picnic.ui.AndroidUiThreadExecutor;
import com.github.rodrigohenriques.picnic.ui.custom.CurrencyFormatter;
import com.github.rodrigohenriques.picnic.ui.custom.NetherlandsCurrencyFormatter;

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

    @Provides @Singleton public CartListPresenter provideCartListPresenter(CartListPresenterImpl cartListPresenter) {
        return cartListPresenter;
    }

    @Provides @Singleton public GetCartListUseCase provideGetCartListUseCase(GetCartListUseCaseImpl getCartListUseCase) {
        return getCartListUseCase;
    }

    @Provides @Singleton public CurrencyFormatter provideCurrencyFormatter(NetherlandsCurrencyFormatter currencyFormatter) {
        return currencyFormatter;
    }
}
