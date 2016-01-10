package com.github.rodrigohenriques.picnic.ui;

import android.os.Handler;
import android.os.Looper;

import com.github.rodrigohenriques.domain.interactor.UiThreadExecutor;

import javax.inject.Inject;

public class AndroidUiThreadExecutor implements UiThreadExecutor {
    Handler handler;

    @Inject
    public AndroidUiThreadExecutor() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
