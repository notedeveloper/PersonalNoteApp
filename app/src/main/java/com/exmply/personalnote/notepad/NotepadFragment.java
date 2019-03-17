package com.exmply.personalnote.notepad;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exmply.personalnote.R;

public class NotepadFragment extends Fragment {
    public NotepadFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_notepad,container,false);
    }
}
