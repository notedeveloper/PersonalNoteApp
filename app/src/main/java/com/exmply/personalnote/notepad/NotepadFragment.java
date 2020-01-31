package com.exmply.personalnote.notepad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.exmply.personalnote.R;

public class NotepadFragment extends Fragment {
    private Button mButton;
    private ListView mList;
    private Intent mIntent;
    private NoteAdapter mAdapter;
    private NoteDB mNotedb;
    private Cursor cursor;
    private SQLiteDatabase dbreader;

    public NotepadFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.notepad_main,container,false);
        return view;
    }
    @SuppressLint("ResourceType")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.layout.activity_main);
        mList = (ListView) this.getActivity().findViewById(R.id.list);
        mButton = this.getActivity().findViewById(R.id.add);
        mNotedb = new NoteDB(this.getActivity());

        dbreader = mNotedb.getReadableDatabase();
        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mIntent = new Intent(NotepadFragment.this.getActivity(),AddContent.class);
                startActivity(mIntent);
            }
        });
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                Intent intent = new Intent(NotepadFragment.this.getActivity(),ShowContent.class);
                intent.putExtra(NoteDB.ID,cursor.getInt(cursor.getColumnIndex(NoteDB.ID)));
                intent.putExtra(NoteDB.CONTENT,cursor.getString(cursor.getColumnIndex(NoteDB.CONTENT)));
                intent.putExtra(NoteDB.TIME,cursor.getString(cursor.getColumnIndex(NoteDB.TIME)));
                startActivity(intent);
            }
        });
    }


    public void add(View v){
        mIntent = new Intent(this.getActivity(),AddContent.class);
        startActivity(mIntent);
    }

    public void selectDb(){
        cursor = dbreader.query(NoteDB.TABLE_NAME,null,null,null,null,null,null);
        mAdapter = new NoteAdapter(this.getContext(),cursor);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        selectDb();
    }
}
