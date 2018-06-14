package org.pkh.fashion.Interface;

import android.content.Intent;

public interface ISplashView extends IBaseView{
    /**
     * show loading screen while loading data from webserver
     */
    public void setLoadingProgress(int progress);

    /**
     * stop loading screen, this is called for completion of
     * loading data from webserver alsp error in loading
     */
    public void stopLoadingProgress();
    public Intent getNextActivity();
    public void moveToNextActivity(Intent intent);
}
