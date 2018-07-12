package com.skilltest.fashion.presenter.main;


import com.skilltest.fashion.Constant;
import com.skilltest.fashion.ui.main.BaseFragment;

public interface MainActivityVP {
    interface View {
        void addFragment(BaseFragment fragment);
    }
    interface Presenter {
        void attachFragment(Constant.FRAGMENT_TYPE type);
    }
}
