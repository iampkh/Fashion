package com.skilltest.fashion.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.skilltest.fashion.network.model.Category;
import com.skilltest.fashion.network.model.Item;

import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NetworkManager {
    private static NetworkManager INSTANCE;

    /**
     * single instance for helper obj
     * @return
     */
    public static NetworkManager getInstance(){
        if ( INSTANCE == null) {
            INSTANCE = new NetworkManager();
        }
        return INSTANCE;
    }

    /**
     * get Network status
     * @param context
     * @return true for active internet otherwise false
     */
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public Single<List<Category>> getCategoryListObservable(){
        NetworkService apiService = NetworkClient.getInstance()
                .create(NetworkService.class);
        return apiService.getCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public Single<List<Item>> getItemListObservable(String url){
        NetworkService apiService = NetworkClient.getInstance()
                .create(NetworkService.class);
        return apiService.getItemList(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
