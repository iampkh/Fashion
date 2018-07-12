package com.skilltest.fashion.network.async;

import android.os.Handler;

import com.skilltest.fashion.presenter.login.OnLoginFinishedListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginAsyncInteractor implements ILoginAsyncInteractor {

    /**
     * Email validator
     */
    private static final String REGEX = "^(.+)@(.+)$";
    Pattern mPattern = Pattern.compile(REGEX);

    public LoginAsyncInteractor(){}


    @Override
    public void validateAccount(final OnLoginFinishedListener listener, final String user, String pswd) {
        //Mocking the network call for login
        //Todo performnetwork call for real time login auth.
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Matcher matcher = mPattern.matcher(user);
                if (matcher.matches()) {
                    listener.onSuccess();
                } else {
                    listener.onFailed();
                }
            }
        }, 2000);
    }
}
