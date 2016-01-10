package com.github.rodrigohenriques.domain.interactor;

public interface UiThreadExecutor {
    void execute(Runnable runnable);
}
