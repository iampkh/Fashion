package com.skilltest.fashion.presenter.main;

import com.skilltest.fashion.ui.main.BaseFragment;

public interface FragmentNavigation {

    interface View {
        void atachPresenter(Presenter presenter);
    }

    interface Presenter {
        void addFragment(BaseFragment fragment);
    }
}
