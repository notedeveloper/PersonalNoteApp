package com.exmply.personalnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


import com.zyyoona7.lock.GestureLockDisplayView;
import com.zyyoona7.lock.GestureLockLayout;
import com.zyyoona7.lock.ILockView;
import com.zyyoona7.lock.JDLockView;
import com.zyyoona7.lock.LockViewFactory;

import java.util.List;

public class LockSettingActivity extends AppCompatActivity {

    private GestureLockLayout mGestureLockLayout;
    private GestureLockDisplayView mLockDisplayView;
    private TextView mSettingHintText;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_setting);
        getSupportActionBar().hide();
        initViews();
        initEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 重置
     */
    private void resetGesture() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mGestureLockLayout.resetGesture();
            }
        }, 200);
    }

    private void initViews() {
        mGestureLockLayout = findViewById(R.id.l_gesture_view);
        mLockDisplayView = findViewById(R.id.l_display_view);
        mSettingHintText = findViewById(R.id.tv_setting_hint);
        //设置提示view 每行每列点的个数
        mLockDisplayView.setDotCount(3);
        //设置提示view 选中状态的颜色
        mLockDisplayView.setDotSelectedColor(Color.parseColor("#01A0E5"));
        //设置提示view 非选中状态的颜色
        mLockDisplayView.setDotUnSelectedColor(Color.TRANSPARENT);
        //设置手势解锁view 每行每列点的个数
        mGestureLockLayout.setDotCount(3);
        //设置手势解锁view 最少连接数
        mGestureLockLayout.setMinCount(3);
        //默认解锁样式为手Q手势解锁样式
        mGestureLockLayout.setLockView(new LockViewFactory() {
            @Override
            public ILockView newLockView() {
                return new JDLockView(LockSettingActivity.this);
            }
        });
        //设置手势解锁view 模式为重置密码模式
        mGestureLockLayout.setMode(GestureLockLayout.RESET_MODE);
    }

    private void initEvents() {
        mGestureLockLayout.setOnLockResetListener(new GestureLockLayout.OnLockResetListener() {
            @Override
            public void onConnectCountUnmatched(int connectCount, int minCount) {
                //连接数小于最小连接数时调用

                mSettingHintText.setText("最少连接" + minCount + "个点");
                resetGesture();
            }

            @Override
            public void onFirstPasswordFinished(List<Integer> answerList) {
                //第一次绘制手势成功时调用

                mSettingHintText.setText("确认解锁图案");
                //将答案设置给提示view
                mLockDisplayView.setAnswer(answerList);
                //重置
                resetGesture();
            }

            @Override
            public void onSetPasswordFinished(boolean isMatched, List<Integer> answerList) {
                //第二次密码绘制成功时调用

                if (isMatched) {
                    //两次答案一致，保存
                    MyApplication.getInstance().answer = answerList.toString();
                    MyApplication.getInstance().isUnlock = false;

                    // 持久化
                    SharedPreferences shp = getSharedPreferences("myApp", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shp.edit();
                    editor.putString("ANSWER", answerList.toString());
                    editor.apply();

                    finish();
                } else {
                    resetGesture();
                }
            }
        });
    }


}
