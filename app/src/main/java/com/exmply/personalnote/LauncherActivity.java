package com.exmply.personalnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends AppCompatActivity {

    private Button setLock;
    private Button startMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initView();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();


        // 从持久化数据中 取出答案
        SharedPreferences shp = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        MyApplication.getInstance().answer = shp.getString("ANSWER", "");

        if (!TextUtils.isEmpty(MyApplication.getInstance().answer)&& !MyApplication.getInstance().isUnlock) {
            goTo(LockingActivity.class);
        }
    }

    public void initView() {
        setLock = findViewById(R.id.setLock);
        startMain = findViewById(R.id.startMain);
    }

    public void initEvent() {
        setLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(LockSettingActivity.class);
            }
        });
        startMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(MainActivity.class);
            }
        });
    }

    public void goTo(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
