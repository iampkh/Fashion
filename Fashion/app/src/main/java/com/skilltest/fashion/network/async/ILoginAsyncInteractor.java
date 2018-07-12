package com.skilltest.fashion.network.async;

import com.skilltest.fashion.presenter.login.OnLoginFinishedListener;

public interface ILoginAsyncInteractor {

    void validateAccount(OnLoginFinishedListener listener, String user, String pswd);
}
