package com.exmply.personalnote.notepad;

import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exmply.personalnote.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class AddContent extends AppCompatActivity {
    private EditText mEditText;
    private NoteDB mNoteDB;
    private SQLiteDatabase mSqldb;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏工具栏
        setContentView(R.layout.notepad_add);
        mEditText = (EditText) this.findViewById(R.id.text);
        mNoteDB = new NoteDB(this);
        mSqldb = mNoteDB.getWritableDatabase();
        saveButton = this.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DbAdd();
                finish();
            }
        });
    }

    public void cancle(View v){
        mEditText.setText("");
        finish();
    }

    private void DbAdd() {
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT,mEditText.getText().toString());
        Log.println(2,"AddContent",mEditText.getText().toString());
        cv.put(NoteDB.TIME,getTime());
        mSqldb.insert(NoteDB.TABLE_NAME,null,cv);
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }
}
