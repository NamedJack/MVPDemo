package com.ultrapower.sc.splash.IActivity;

/**
 * Created by Administrator on 2016/12/20.
 */

public interface ISplashActivity {
    public String getUserName();
    public String getPassWord();
    public boolean saveUserInfo();
    public boolean clearUserInfo();
    public boolean isEmptyUserName(String userNmae);
    public boolean isEmptyUserPassWord(String passWord);
    public void showLanding();
    public void dismissLanding();
    public void toHomeActivity();

}
