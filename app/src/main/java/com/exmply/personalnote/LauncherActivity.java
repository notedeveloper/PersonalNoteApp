package com.exmply.personalnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    }

    public void initView() {
        setLock = findViewById(R.id.setLock);
        startMain = findViewById(R.id.startMain);
    }

    public void initEvent() {
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
