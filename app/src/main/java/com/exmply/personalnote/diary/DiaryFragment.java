package com.exmply.personalnote.diary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.exmply.personalnote.R;
import com.exmply.personalnote.diary.activities.AddContent;
import com.exmply.personalnote.diary.activities.ShowContent;
import com.exmply.personalnote.diary.adapter.DiaryAdapter;
import com.exmply.personalnote.diary.db.DiaryDB;

public class DiaryFragment extends Fragment {
    private Button mButton;
    private ListView mList;
    private Intent mIntent;
    private DiaryAdapter mAdapter;
    private DiaryDB mDiaryDB;
    private Cursor cursor;
    private SQLiteDatabase dbreader;

    public DiaryFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_diary,container,false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.layout.activity_main);
        mList = (ListView) this.getActivity().findViewById(R.id.diary_list);
        mButton = this.getActivity().findViewById(R.id.diary_add);
        mDiaryDB = new DiaryDB(this.getActivity());

        dbreader = mDiaryDB.getReadableDatabase();
        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mIntent = new Intent(DiaryFragment.this.getActivity(), AddContent.class);
                startActivity(mIntent);
            }
        });
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                Intent intent = new Intent(DiaryFragment.this.getActivity(), ShowContent.class);
                intent.putExtra(DiaryDB.ID,cursor.getInt(cursor.getColumnIndex(DiaryDB.ID)));
                intent.putExtra(DiaryDB.CONTENT,cursor.getString(cursor.getColumnIndex(DiaryDB.CONTENT)));
                intent.putExtra(DiaryDB.TIME,cursor.getString(cursor.getColumnIndex(DiaryDB.TIME)));
                startActivity(intent);
            }
        });
    }

    public void addDiary(View v){
        mIntent = new Intent(this.getActivity(),AddContent.class);
        startActivity(mIntent);
    }

    public void selectDb(){
        cursor = dbreader.query(DiaryDB.TABLE_NAME,null,null,null,null,null,null);
        mAdapter = new DiaryAdapter(this.getContext(),cursor);
        mList.setAdapter(mAdapter);

    }

    @Override
    public void onResume(){
        super.onResume();
        selectDb();
    }
}
