package org.pkh.fashion.main.home;

import org.pkh.fashion.Interface.IBaseView;
import org.pkh.fashion.Interface.IHomePresenter;
import org.pkh.fashion.utils.Logger;
import org.pkh.fashion.utils.FashionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter class for Home fragment,
 * This presenter will handle all the Home operations
 * (Background operation)
 */
public class HomePresenter implements IHomePresenter {
    //Todo need to implement Home background logic.
    List<IBaseView> mBaseViewList ;

    public HomePresenter() {
    }

    @Override
    public void orientationChange(FashionUtil.ORIENTATION orientation) {
        Logger.logD("Home Presenter onConfigurationChange="+orientation);
        for(IBaseView baseView:mBaseViewList){
            Logger.logD("baseview  onConfigurationChange frag="+orientation);
            baseView.onConfigurationChange(orientation);
        }
    }

    @Override
    public void init() {
        mBaseViewList = new ArrayList<>();
    }

    @Override
    public void destroy() {
        mBaseViewList = null;
    }

    public void addView(IBaseView baseView) {
        if (mBaseViewList != null && baseView != null && !mBaseViewList.contains(baseView)){
            mBaseViewList.add(baseView);
        }

    }

}
