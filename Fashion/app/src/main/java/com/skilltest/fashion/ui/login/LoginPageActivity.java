package com.skilltest.fashion.ui.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skilltest.fashion.Constant;
import com.skilltest.fashion.R;
import com.skilltest.fashion.presenter.login.LoginPresenter;
import com.skilltest.fashion.presenter.login.LoginVP;
import com.skilltest.fashion.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginPageActivity extends AppCompatActivity implements LoginVP.View{

    //declaring view object
    @BindView(R.id.input_email)EditText mEmailEditText;
    @BindView(R.id.input_password)EditText mPassworddEditText;
    @BindView(R.id.btn_login)Button mLoginButton;
    ProgressDialog mAuthenticatingProgressBar;
    AlertDialog mEnableWifiAlertDialog;

    //declaring support class/object
    LoginPresenter mLoginPreseneter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(android.R.layout.act);
        setContentView(R.layout.activity_login_page);
        ButterKnife.bind(this); // initiation of ButterKnife

        //initiating progress bar for authentication
        mAuthenticatingProgressBar = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        mAuthenticatingProgressBar.setIndeterminate(true);
        mAuthenticatingProgressBar.setMessage(getString(R.string.authnticating));

        //initiating dialog instance for network notification.
        mEnableWifiAlertDialog = new AlertDialog.Builder(this).create();
        mEnableWifiAlertDialog.setMessage(getString(R.string.alert_dialog_desc));
        mEnableWifiAlertDialog.setTitle(getString(R.string.alert_dialog_Title));
        //ToDo update code to functional programming. (d,i)-> { startActivity  } for mEnableWifiAlertDialog interface

        mEnableWifiAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,
                this.getString(R.string.alert_dialog_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(Constant.SETTINGS_PACKAGE,
                                Constant.INTERNET_ENABLE_CLASS));
                        startActivity(intent);
                    }
                });


        //initiating support objects
        mLoginPreseneter = new LoginPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        popUpNetworkEnableDialogIfNeed();
    }

    private void popUpNetworkEnableDialogIfNeed(){
        boolean isNetworkAvail = mLoginPreseneter.isNetworkAvailable(this);
        if(!isNetworkAvail){
            mEnableWifiAlertDialog.show();
        }
    }

    @OnClick(R.id.btn_login)
    public void performLogin(){

        String email = mEmailEditText.getText().toString();
        String passwd = mPassworddEditText.getText().toString();
        if (email != null && !email.isEmpty()
                && passwd != null && !passwd.isEmpty()) {
            mAuthenticatingProgressBar.show();
            mLoginPreseneter.doLogin(email, passwd);
        }else{
            Toast.makeText(this, this.getString(R.string.error_no_valid_credential), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void navigateToNextScreen() {
        mAuthenticatingProgressBar.dismiss();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginFailed() {
        mAuthenticatingProgressBar.dismiss();
        Toast.makeText(this, this.getString(R.string.msg_login_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPreseneter.destroy();
        mEnableWifiAlertDialog = null;
        mAuthenticatingProgressBar = null;
    }
}

