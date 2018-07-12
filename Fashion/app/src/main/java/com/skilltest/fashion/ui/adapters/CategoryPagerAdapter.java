package com.skilltest.fashion.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.skilltest.fashion.network.model.Category;
import com.skilltest.fashion.ui.main.ItemListFragment;
import com.skilltest.fashion.ui.main.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {
    List<Category> mCategoryList = null;
    OnItemClickListener mItemClickListner;

    public CategoryPagerAdapter(FragmentManager fm, OnItemClickListener listener) {
        super(fm);
        mItemClickListner =listener;
    }

    public void setCategoryList(List<Category> categoryList){
        if(categoryList != null ) {
            mCategoryList = categoryList;
        }
    }

    @Override
    public int getCount() {
        return mCategoryList !=null ? mCategoryList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position < getCount() ? mCategoryList.get(position).getName() : "";
    }

    @Override
    public Fragment getItem(int position) {
        Category category = position < getCount() ? mCategoryList.get(position) : null;
        ItemListFragment fragment = ItemListFragment.newInstance(category,mItemClickListner);
        return fragment;
    }

}