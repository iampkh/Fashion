package com.skilltest.fashion.presenter.main;

import com.skilltest.fashion.Constant;
import com.skilltest.fashion.ui.main.BaseFragment;
import com.skilltest.fashion.ui.main.DetailFragment;
import com.skilltest.fashion.ui.main.ItemCategoryFragment;

public class MainActivityPresenter implements MainActivityVP.Presenter,FragmentNavigation.Presenter {

    private MainActivityVP.View mView;
    private BaseFragment mBaseFragment;
    /**
     * construtor for presenter
     * @param view Mvp view object.
     */
    public MainActivityPresenter(MainActivityVP.View view){
        mView = view;
    }

    @Override
    public void attachFragment(Constant.FRAGMENT_TYPE type) {
        switch (type){
            case MAIN:
                mBaseFragment = ItemCategoryFragment.newInstance();
                break;
            case DETAIL:
                mBaseFragment = DetailFragment.newInstance();
                break;
            case CART:
                //TODO: initiate the fragment
                //mBaseFragment = ItemCategoryFragment.newInstance();
                break;
            case PAYMENT:
                //mBaseFragment = ItemCategoryFragment.newInstance();
                break;
                default:
        }

        if( mBaseFragment != null) {
            mView.addFragment(mBaseFragment);
        }

    }

    @Override
    public void addFragment(BaseFragment fragment) {
        mView.addFragment(mBaseFragment);
    }
}
