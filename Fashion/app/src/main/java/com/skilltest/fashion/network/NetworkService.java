package com.skilltest.fashion.network;

import com.skilltest.fashion.network.model.Category;
import com.skilltest.fashion.network.model.Item;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NetworkService {

    @GET(NetworkClient.ModelList_URL)
    public Single<List<Category>> getCategoryList();

    @GET
    public Single<List<Item>> getItemList(@Url String url);
}
