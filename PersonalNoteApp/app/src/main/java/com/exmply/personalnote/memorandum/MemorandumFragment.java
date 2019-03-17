package com.exmply.personalnote.memorandum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exmply.personalnote.R;

public class MemorandumFragment extends Fragment {
    public MemorandumFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_memorandum,container,false);
    }
}
