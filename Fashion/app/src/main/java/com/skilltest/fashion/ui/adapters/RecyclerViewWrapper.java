package com.skilltest.fashion.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class RecyclerViewWrapper {

    /**
     * Recycler view which need to be wrapped.
     */
    private RecyclerView mRecyclerView;

    /**
     * Implement get current position logic
     * @return
     */
    public abstract int getCurrentPosition();

    public interface OnItemClickListner {
        void onItemClick(int position);
    }

    public RecyclerViewWrapper(RecyclerView recyclerView){
        if(recyclerView == null) { throw new NullPointerException(); }
        this.mRecyclerView = recyclerView;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setPosition(int position){
        mRecyclerView.scrollToPosition(position);
        mRecyclerView.invalidate();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter){
        mRecyclerView.setAdapter(adapter);
    }

    public RecyclerView.LayoutManager getLayoutManager(){
        return mRecyclerView.getLayoutManager();
    }

    public RecyclerView.Adapter getAdapter() {
        return mRecyclerView.getAdapter();
    }
}
