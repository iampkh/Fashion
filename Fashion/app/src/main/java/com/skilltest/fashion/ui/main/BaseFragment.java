package com.skilltest.fashion.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skilltest.fashion.presenter.main.FragmentNavigation;

public abstract class BaseFragment extends Fragment implements FragmentNavigation.View{

    protected View mRootView;
    /**
     * presenter for fragment navigation
     */
    protected FragmentNavigation.Presenter mNavigationPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = super.onCreateView(inflater, container, savedInstanceState);
        return mRootView;
    }

    @Override
    public void atachPresenter(FragmentNavigation.Presenter presenter) {
        mNavigationPresenter = presenter;

    }

    /**
     * The view instantiated in onCreateView
     * Thus it should not call before onCreateView();
     * @return
     */
    protected View getRootView(){
        return mRootView;
    }
}
