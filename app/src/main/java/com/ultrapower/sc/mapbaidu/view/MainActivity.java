package com.ultrapower.sc.mapbaidu.view;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ultrapower.sc.mapbaidu.R;
import com.ultrapower.sc.mapbaidu.presenter.PresenterOfMainActivity;

import static com.ultrapower.sc.mapbaidu.R.id.navigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,PresenterOfMainActivity {

//    baidu appKEY = "34dbade1ebde5a8b13e42e2fe1e4b871";
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private Boolean keyBackClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initID();
        initLocation();
        mToolbar.setTitle("设置标题");
        setSupportActionBar(mToolbar);
        mNavigationView = (NavigationView) findViewById(navigationView);
        mNavigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setHomeButtonEnabled(true);//返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(// 构建抽屉的监听
                this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();// 根据drawerlayout同步其当前状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initLocation() {
    }

    private void initID() {
        mToolbar = (Toolbar) findViewById(R.id.layout_toolbar);
        mFrameLayout = (FrameLayout) findViewById(R.id.main_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout1);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()){
            item.setChecked(false);
        }
        switch (item.getItemId()){
            case R.id.github_hot_coder:
                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                break;
            case R.id.github_hot_repo:
                Toast.makeText(MainActivity.this, "234", Toast.LENGTH_SHORT).show();
                break;
            case R.id.github_hot_trend:
                Toast.makeText(MainActivity.this, "345", Toast.LENGTH_SHORT).show();
                break;
            case R.id.arsenal_my_repo:
                Toast.makeText(MainActivity.this, "456", Toast.LENGTH_SHORT).show();
                break;
        }
        // 关闭drawerLayout
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        // 返回true，代表将该菜单项变为checked状态
        return true;
    }



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(!keyBackClick){
                keyBackClick = true;
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
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
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void navigateToMain() {

    }

    @Override
    public void showProgress() {

    }
}
