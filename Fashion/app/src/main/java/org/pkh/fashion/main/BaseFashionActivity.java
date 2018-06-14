package org.pkh.fashion.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.pkh.fashion.Interface.IBaseView;
import org.pkh.fashion.Interface.IListener;
import org.pkh.fashion.utils.Logger;
import org.pkh.fashion.utils.FashionUtil;

/**
 * Base Activity which handled the android callback
 * All the other activities used in this app should extend this activity.
 */
public abstract class BaseFashionActivity extends AppCompatActivity implements IBaseView {

    protected abstract void destroy();
    protected abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        init();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        Logger.logD("BaseMercariActivity  onConfigurationChange frag=");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            onConfigurationChange(FashionUtil.ORIENTATION.LANDSCAPE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            onConfigurationChange(FashionUtil.ORIENTATION.POTRAIT);
        }
    }
}
