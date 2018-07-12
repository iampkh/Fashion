package com.skilltest.fashion.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.skilltest.fashion.Constant;
import com.skilltest.fashion.R;
import com.skilltest.fashion.presenter.main.MainActivityPresenter;
import com.skilltest.fashion.presenter.main.MainActivityVP;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityVP.View {

    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        //initiating toolbar.
        attachCustomToolBar();

        //initiating objects
        mPresenter = new MainActivityPresenter(this);
        mPresenter.attachFragment(Constant.FRAGMENT_TYPE.MAIN);

    }

    /**
     * Attaching tool bar to activity
     * Any customization for tool bar should go in this method.
     *
     */
    private void attachCustomToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public void addFragment(BaseFragment fragment) {
        fragment.atachPresenter(mPresenter);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }
}
