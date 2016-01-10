package com.github.rodrigohenriques.domain.interactor;

import java.io.IOException;

public abstract class AbstractUseCase<Input, Result> implements UseCase {
    protected Input mInput;
    protected Callback<Result> mCallback;
    protected UiThreadExecutor mUiThreadExecutor;
    protected Thread mProcess;

    public AbstractUseCase(UiThreadExecutor mUiThreadExecutor) {
        this.mUiThreadExecutor = mUiThreadExecutor;
    }

    protected void executeAsync(Input input, Callback<Result> callback) {
        mInput = input;
        mCallback = callback;
        mCallback.setUiThreadExecutor(mUiThreadExecutor);

        mProcess = new Thread(this);
        mProcess.start();
    }

    @Override
    public void run() {
        try {
            Result result = executeOnBackground(mInput);

            mCallback.dispatchResult(result);
        } catch (Exception e) {
            mCallback.dispatchException(e);
        }
    }

    protected abstract Result executeOnBackground(Input input) throws IOException;
}
