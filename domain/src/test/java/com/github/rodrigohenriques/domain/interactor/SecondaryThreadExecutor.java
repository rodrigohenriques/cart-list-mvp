package com.github.rodrigohenriques.domain.interactor;

class SecondaryThreadExecutor implements UiThreadExecutor {
    @Override
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}