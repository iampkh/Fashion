package com.skilltest.fashion.presenter.login;

import android.content.Context;

import com.skilltest.fashion.network.async.ILoginAsyncInteractor;
import com.skilltest.fashion.network.NetworkManager;
import com.skilltest.fashion.network.async.LoginAsyncInteractor;

public class LoginPresenter implements LoginVP.Presenter,OnLoginFinishedListener {
    //Todo Initialization need to be achieved by dagger2

    /**
     * View interface for LoginPageActivity activity.
     */
    private LoginVP.View mLoginView;
    private ILoginAsyncInteractor mInteractor;

    private LoginPresenter(){}

    public LoginPresenter(LoginVP.View loginView){
        mLoginView = loginView;
        mInteractor = new LoginAsyncInteractor();
    }

    /**
     * callback to presenter when login is success.
     */
    @Override
    public void onSuccess() {
        if(mLoginView !=null) {mLoginView.navigateToNextScreen(); }
    }

    /**
     * callback to presenter when login is failed.
     */
    @Override
    public void onFailed() {
        if(mLoginView !=null){ mLoginView.loginFailed();}
    }

    @Override
    public void doLogin(String username, String pswd) {
        mInteractor.validateAccount(this,username,pswd);
    }

    @Override
    public void destroy() {
        mLoginView = null;
    }

    public boolean isNetworkAvailable(Context context) {
        return NetworkManager.getInstance().isNetworkAvailable(context);
    }
}
