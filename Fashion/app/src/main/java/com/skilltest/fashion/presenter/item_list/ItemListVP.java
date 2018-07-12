package com.skilltest.fashion.presenter.item_list;

import com.skilltest.fashion.network.model.Category;
import com.skilltest.fashion.network.model.Item;
import com.skilltest.fashion.ui.adapters.ItemListAdapter;

import java.util.List;

public interface ItemListVP {
    interface  View{
        void onServerSuccess(List<Item> itemList);
        void onServerFailed();
    }
    interface Presenter {
        void fetchServerData(String url);
        void destroy();
    }
}
