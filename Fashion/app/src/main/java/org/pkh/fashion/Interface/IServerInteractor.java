package org.pkh.fashion.Interface;

import org.pkh.fashion.Model.Category;

import java.util.List;

public interface IServerInteractor {


    interface OnLoadedJsonListener {
        void onLoaded(List<Category> itemCategory);
        void onLoading(int progress);
    }

    void getJsonFromServer(OnLoadedJsonListener listener);

}
