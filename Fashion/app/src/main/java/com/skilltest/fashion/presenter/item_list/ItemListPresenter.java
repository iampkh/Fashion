package com.skilltest.fashion.presenter.item_list;

import android.util.Log;

import com.skilltest.fashion.network.NetworkManager;
import com.skilltest.fashion.network.model.Category;
import com.skilltest.fashion.network.model.Item;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class ItemListPresenter implements ItemListVP.Presenter {

    ItemListVP.View mView;
    NetworkManager mNetworkManager;
    private Single<List<Item>> mSingleObservable;

    public ItemListPresenter(ItemListVP.View view){
        mView = view;
        mNetworkManager = NetworkManager.getInstance();
    }

    public SingleObserver<List<Item>> getDisposableObserverForItemList(){
        return new DisposableSingleObserver<List<Item>>() {
            @Override
            public void onSuccess(List<Item> itemList) {
                if(mView != null ) {mView.onServerSuccess(itemList);}
            }

            @Override
            public void onError(Throwable e) {
                // Network error
                if(mView != null ) {mView.onServerFailed();}
            }
        };
    }

    @Override
    public void fetchServerData(String url) {
        mSingleObservable = mNetworkManager.getItemListObservable(url);
        mSingleObservable.subscribeWith(getDisposableObserverForItemList());

    }

    @Override
    public void destroy() {
        mView = null;
    }
}
