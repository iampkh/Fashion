package com.skilltest.fashion.presenter.category_list;

import com.skilltest.fashion.network.model.Category;

import java.util.List;

public interface ItemCategoryVP {

    interface  View{
        void onServerSuccess(List<Category> categories);
        void onServerFailed();
    }
    interface Presenter {
        void fetchServerData();
        void cameraLaunchAction();
        void destroy();
    }
}
