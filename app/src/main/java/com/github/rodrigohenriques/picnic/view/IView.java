package com.github.rodrigohenriques.picnic.view;

public interface IView {
    void showLoading();
    void hideLoading();
    void showError(String message);
}
