package com.exmply.personalnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.zyyoona7.lock.GestureLockDisplayView;
import com.zyyoona7.lock.GestureLockLayout;
import com.zyyoona7.lock.ILockView;
import com.zyyoona7.lock.JDLockView;
import com.zyyoona7.lock.LockViewFactory;

public class LockSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_setting);
    }
}
