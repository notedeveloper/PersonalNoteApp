package com.exmply.personalnote;

import android.os.Bundle;

import androidx.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;

public class LockingActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locking);
        getSupportActionBar().hide();

        loadRootFragment(R.id.fl_fragment_container, LockFragment.newInstance());
    }
}
