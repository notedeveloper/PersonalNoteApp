package com.exmply.personalnote.diary.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exmply.personalnote.R;
import com.exmply.personalnote.diary.db.DiaryDB;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContent extends AppCompatActivity {
    private EditText mEditText;
    private DiaryDB mDiaryDB;
    private SQLiteDatabase mSqldb;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏工具栏
        setContentView(R.layout.diary_add);
        mEditText = (EditText) this.findViewById(R.id.diary_text);
        mDiaryDB = new DiaryDB(this);
        mSqldb = mDiaryDB.getWritableDatabase();
//        saveButton = this.findViewById(R.id.diary_save);
//        saveButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                DbAdd();
//                finish();
//            }
//        });
    }

    public void cancleDiary(View v){
        mEditText.setText("");
        finish();
    }
    public void saveDiary(View v){
        DbAdd();
        finish();
    }

    private void DbAdd() {
        ContentValues cv = new ContentValues();
        cv.put(mDiaryDB.CONTENT,mEditText.getText().toString());
        Log.println(2,"AddContent",mEditText.getText().toString());
        cv.put(mDiaryDB.TIME,getTime());
        mSqldb.insert(mDiaryDB.TABLE_NAME,null,cv);
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }
}
