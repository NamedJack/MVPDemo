package com.ultrapower.sc.splash.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ultrapower.sc.mapbaidu.R;
import com.ultrapower.sc.mapbaidu.view.MainActivity;
import com.ultrapower.sc.splash.IActivity.ISplashActivity;
import com.ultrapower.sc.splash.presenter.SplashPresenter;

import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity implements ISplashActivity{

    private EditText userName,userPassword;
    private CheckBox rememberPass;
    private Button mButton;
    private GifImageView gifImageView;
    private boolean keyBackClick;

    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findID();

    }

    private void findID() {
        userName = (EditText) findViewById(R.id.splash_et_username);
        userPassword = (EditText) findViewById(R.id.splash_et_userpassword);
        rememberPass = (CheckBox) findViewById(R.id.splash_cb_rememberword);
        gifImageView = (GifImageView) findViewById(R.id.splash_img_anim);
        mButton = (Button) findViewById(R.id.splash_btn_commit);
        mButton.setOnClickListener(btnClickListener);
        splashPresenter = new SplashPresenter(this);
    }
    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            splashPresenter.login();
        }
    };

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getPassWord() {
        return userPassword.getText().toString();
    }

    @Override
    public boolean saveUserInfo() {
        return false;
    }

    @Override
    public boolean clearUserInfo() {
        return false;
    }

    @Override
    public boolean isEmptyUserName(String userNmae) {
        if(TextUtils.isEmpty(userNmae)){
            userName.setText("用户名不能为空");
            userName.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmptyUserPassWord(String passWord) {
        if(TextUtils.isEmpty(passWord)){
            userPassword.setText("密码为空");
            userPassword.requestFocus();
            return false;
        }
        return true;
    }


    @Override
    public void showLanding() {
        //软键盘取消
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
        gifImageView.setVisibility(View.VISIBLE);
        userName.setFocusable(false);
        userPassword.setFocusable(false);
        mButton.setText("登录中...");
    }

    @Override
    public void dismissLanding() {
        gifImageView.setVisibility(View.GONE);
    }

    @Override
    public void toHomeActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }

    public void checkedPhoneNet(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info == null || !manager.getBackgroundDataSetting()){
            return ;
        }
        return;

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            mButton.setText("登录");
//            gifImageView.setVisibility(View.GONE);
//            userName.setFocusable(true);
//            userPassword.setFocusable(true);
//            rememberPass.setFocusable(true);
//        }
//        return super.onKeyDown(keyCode, event);
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(!keyBackClick){
                keyBackClick = true;
                mButton.setText("登录");
                gifImageView.setVisibility(View.GONE);
                userName.setFocusable(true);
                userPassword.setFocusable(true);
                Toast.makeText(SplashActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.currentThread().sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        keyBackClick = false;
                    }
                }.start();
                return true;
            }else {
                finish();
                Process.killProcess(Process.myPid());
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
