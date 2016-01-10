package com.github.rodrigohenriques.picnic.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.rodrigohenriques.picnic.AndroidApplication;
import com.github.rodrigohenriques.picnic.R;
import com.github.rodrigohenriques.picnic.di.AppComponent;
import com.github.rodrigohenriques.picnic.view.IView;

public abstract class BaseActivity extends AppCompatActivity implements IView {

    protected AppComponent mApplicationComponent;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplicationComponent =  ((AndroidApplication) getApplication()).getApplicationComponent();
        mApplicationComponent.inject(this);
    }

    @Override
    public void showLoading() {
        CharSequence title = getString(R.string.progress_title);
        CharSequence message = getString(R.string.progress_message);

        mProgressDialog = ProgressDialog.show(this, title, message, true, false);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.setAction("Fechar", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
