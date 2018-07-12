package com.skilltest.fashion.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.skilltest.fashion.R;
import com.skilltest.fashion.network.model.Item;
import com.skilltest.fashion.presenter.category_list.ItemCategoryPresenter;
import com.skilltest.fashion.presenter.category_list.ItemCategoryVP;
import com.skilltest.fashion.network.model.Category;
import com.skilltest.fashion.ui.adapters.CategoryPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ItemCategoryFragment extends BaseFragment  implements OnItemClickListener,
        ItemCategoryVP.View{

    @BindView(R.id.fab_camera)FloatingActionButton mFloatingActionBtn;
    @BindView(R.id.categoryPager)ViewPager mViewPager;
    @BindView(R.id.progressBar)ProgressBar mProgressBar;

    CategoryPagerAdapter mPagerAdapter = null;

    private ItemCategoryVP.Presenter mPresenter;
    private Unbinder mUnBinder;

    public static ItemCategoryFragment newInstance(){
        return  new ItemCategoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        mUnBinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPagerAdapter = new CategoryPagerAdapter(getFragmentManager(),this);
        mViewPager.setAdapter(mPagerAdapter);

        mPresenter = new ItemCategoryPresenter(this);
        mPresenter.fetchServerData();

    }

    @OnClick(R.id.fab_camera)
    public void onCameraBtnClick(){
        mPresenter.cameraLaunchAction();
        Toast.makeText(getActivity(),"Fab button implementation", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        mUnBinder.unbind();
    }

    @Override
    public void onServerSuccess(List<Category> categories) {
        mPagerAdapter.setCategoryList(categories);
        mPagerAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(ViewGroup.GONE);
    }

    @Override
    public void onServerFailed() {
    }

    @Override
    public void onClick(Item item) {
        //TodO this is an dummy implementation for the next screen
        //modify it for
        Toast.makeText(getActivity(),"Item "+item.getName()+" is Clicked" ,Toast.LENGTH_SHORT).show();
        //mNavigationPresenter.addFragment(DetailFragment.newInstance());
    }
}
