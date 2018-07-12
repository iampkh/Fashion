package com.skilltest.fashion.presenter.login;

public interface LoginVP {

    interface View{
        public void navigateToNextScreen();
        public void loginFailed();
    }
    interface Presenter{
        void doLogin(String username,String pswd);
        void destroy();
    }
}
