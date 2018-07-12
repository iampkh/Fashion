package com.skilltest.fashion.ui.adapters;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class GridViewRecyclerWrapper extends RecyclerViewWrapper {

    public GridViewRecyclerWrapper(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public int getCurrentPosition() {
        return ((GridLayoutManager) getRecyclerView().
                getLayoutManager()).findFirstVisibleItemPosition();
    }

    public void setSpanCount(int count){
        ((GridLayoutManager) getRecyclerView().getLayoutManager()).setSpanCount(count);
        getRecyclerView().invalidate();
    }

}
