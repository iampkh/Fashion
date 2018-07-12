package com.skilltest.fashion.ui.main;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skilltest.fashion.Constant;
import com.skilltest.fashion.R;
import com.skilltest.fashion.network.model.Category;
import com.skilltest.fashion.network.model.Item;
import com.skilltest.fashion.presenter.item_list.ItemListPresenter;
import com.skilltest.fashion.presenter.item_list.ItemListVP;
import com.skilltest.fashion.ui.adapters.GridViewRecyclerWrapper;
import com.skilltest.fashion.ui.adapters.ItemListAdapter;
import com.skilltest.fashion.ui.adapters.RecyclerViewWrapper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemListFragment extends Fragment implements RecyclerViewWrapper.OnItemClickListner,
        ItemListVP.View {
    private Unbinder mUnbiner;
    @BindView(R.id.item_recycler_list)RecyclerView mItemRecyclerView;

    private Category mCategory;
    private OnItemClickListener mOnItemClickListner;
    private GridViewRecyclerWrapper mRecyclerWrapper;

    private int mListSavedPosition;


    ItemListPresenter mItemListPresenter;

    public static ItemListFragment newInstance(Category category,OnItemClickListener listener){
        ItemListFragment fragment = new ItemListFragment();
        fragment.setCategory(category);
        fragment.setOnItemClickListner(listener);
        return fragment;
    }

    public void setCategory(Category category){
        mCategory = category;
    }

    public void setOnItemClickListner(OnItemClickListener listener){
        mOnItemClickListner = listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_fragment,container,false);
        mUnbiner = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerWrapper = new GridViewRecyclerWrapper(mItemRecyclerView);
        mRecyclerWrapper.setAdapter(new ItemListAdapter(this));
        mRecyclerWrapper.setLayoutManager(new GridLayoutManager(getActivity(),2));

        if (savedInstanceState != null){
            mListSavedPosition = savedInstanceState.getInt(Constant.FRAGMENT_ITEM_POS_KEY);
            mRecyclerWrapper.setPosition(mListSavedPosition);
        }

        mItemListPresenter = new ItemListPresenter(this);
        mItemListPresenter.fetchServerData(mCategory.getData());
    }

    @Override
    public void onResume() {
        super.onResume();
        onConfigurationChanged(getResources().getConfiguration());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        saveInstance(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mItemListPresenter.destroy();
        mUnbiner.unbind();
        mRecyclerWrapper = null;

    }

    @Override
    public void onServerSuccess(List<Item> itemList) {
        if( mRecyclerWrapper != null) {
            ((ItemListAdapter) mRecyclerWrapper.getAdapter()).setItemList(itemList);
            mRecyclerWrapper.setPosition(mListSavedPosition);
        }
    }

    @Override
    public void onServerFailed() {

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if( mRecyclerWrapper == null) {
            return;
        }
        switch (newConfig.orientation){

            case Configuration.ORIENTATION_LANDSCAPE:
                mRecyclerWrapper.setSpanCount(3);
                break;
            case  Configuration.ORIENTATION_PORTRAIT:
                mRecyclerWrapper.setSpanCount(2);
                break;
            default:
        }
    }

    private void saveInstance(Bundle instance){
        if(instance != null) {
            if( mRecyclerWrapper == null) {return;}
            instance.putInt(Constant.FRAGMENT_ITEM_POS_KEY,
                    mRecyclerWrapper.getCurrentPosition());
        }
    }

    @Override
    public void onItemClick(int position) {
        //TODO this implemenation should be updated
        if (mOnItemClickListner != null && mRecyclerWrapper != null) {
            Log.d("debug"," onclickeds="+position);
            Item item = ((ItemListAdapter)mRecyclerWrapper.getAdapter())
                    .getItemList().get(position);
            mOnItemClickListner.onClick(item);
        }
    }
}
