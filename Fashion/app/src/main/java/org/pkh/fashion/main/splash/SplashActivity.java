package org.pkh.fashion.main.splash;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.ProgressBar;

import org.pkh.fashion.Interface.ISplashView;
import org.pkh.fashion.R;
import org.pkh.fashion.main.BaseFashionActivity;
import org.pkh.fashion.main.home.FashionHomeActivity;
import org.pkh.fashion.utils.Logger;
import org.pkh.fashion.utils.FashionUtil;

import static org.pkh.fashion.utils.FashionUtil.SCREEN.HOME;

public class SplashActivity extends BaseFashionActivity implements ISplashView {

    /**
     * progress bar to show the progress for loading json from server
     */
    private ProgressBar mProgressBar;
    /**
     * Presenter(Background logic) object for Splash screen,
     * Note: All the UI interaction are made independent.
     */
    private SplashPresenter mSplashPresenter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mSplashPresenter = new SplashPresenter(this);
        mSplashPresenter.init();
        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

    }
    @Override
    protected void init() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void destroy() {
        mSplashPresenter.destroy();
        mSplashPresenter = null;
        mProgressBar = null;
    }

    @Override
    protected void onResume() {
        //TODO : Need to add RequestPermission for internet access.
        super.onResume();
        if(mSplashPresenter != null) {
            if (!mSplashPresenter.isNetworkConnecte()) {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Info");
                alertDialog.setMessage("Please connect to any network");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent();
                                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                                startActivity(intent);
                            }
                        });
                alertDialog.show();

            }else {
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{Manifest.permission.INTERNET},
                        1);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(mSplashPresenter != null) {
                if(!mSplashPresenter.isNetworkConnecte()){
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                    startActivity(intent);
                }
                mSplashPresenter.getDataFromServer();
            }

        }else {
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mSplashPresenter != null) {
            mSplashPresenter.stopFetchingFromServer();
        }
    }

    @Override
    public void setLoadingProgress(int progress) {
        Logger.logI("Progress ="+progress);
        if(mProgressBar != null) {
            mProgressBar.setProgress(progress);
        }
    }

    @Override
    public void stopLoadingProgress() {
        if(mProgressBar != null) {
            mProgressBar.setProgress(100);
        }
    }


    @Override
    public Intent getNextActivity() {
        //ToDo :In future ,If the app launched from notificatoin then by using the enum,directly next
        //screen can be launched
        FashionUtil.SCREEN screenDefault = HOME;
        Intent intent = null;
        switch (screenDefault){
            case HOME:
                intent = FashionHomeActivity.getIntent(this);
                break;
            case DETAIL:
                break;
            default:
                break;
        }

        return intent;
    }



    @Override
    public void moveToNextActivity(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    public void onConfigurationChange(FashionUtil.ORIENTATION orientation) {

    }
}
