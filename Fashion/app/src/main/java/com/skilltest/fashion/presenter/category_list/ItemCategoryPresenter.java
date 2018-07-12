package com.skilltest.fashion.presenter.category_list;

import android.util.Log;

import com.skilltest.fashion.network.NetworkManager;
import com.skilltest.fashion.network.model.Category;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.observers.DisposableSingleObserver;


public class ItemCategoryPresenter implements ItemCategoryVP.Presenter {

    ItemCategoryVP.View mView;
    NetworkManager mNetworkManager;
    private Single<List<Category>> mSingleObservable;

    public ItemCategoryPresenter(ItemCategoryVP.View view){
        mView = view;
        mNetworkManager = NetworkManager.getInstance();
    }

    public SingleObserver<List<Category>> getDisposableObserverForCategoryList(){
        return new DisposableSingleObserver<List<Category>>() {
            @Override
            public void onSuccess(List<Category> categories) {
                if(mView !=null){mView.onServerSuccess(categories);}
            }

            @Override
            public void onError(Throwable e) {
                // Network error
                if(mView !=null){mView.onServerFailed();}
            }
        };
    }

    @Override
    public void fetchServerData() {
        mSingleObservable = mNetworkManager.getCategoryListObservable();
        mSingleObservable.subscribeWith(getDisposableObserverForCategoryList());
    }

    @Override
    public void cameraLaunchAction() {
        //ToDo FAB button support implementation goes here.
    }

    @Override
    public void destroy() {
        mView = null;
    }
}