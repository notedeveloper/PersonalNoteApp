package com.exmply.personalnote.diary.activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.exmply.personalnote.R;
import com.exmply.personalnote.diary.db.DiaryDB;


public class ShowContent extends AppCompatActivity {
    private TextView mTextview;
    private TextView time;
    private DiaryDB mDb;
    private SQLiteDatabase mSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏工具栏
        setContentView(R.layout.diary_view);
        mTextview = (TextView)this.findViewById(R.id.diary_showtext);
        time = (TextView)this.findViewById(R.id.diary_showtime);
        mDb = new DiaryDB(this);
        mSql = mDb.getWritableDatabase();
        mTextview.setText(getIntent().getStringExtra(DiaryDB.CONTENT));
        time.setText(getIntent().getStringExtra(DiaryDB.TIME));
    }
    public void delete(View v) {
        int id = getIntent().getIntExtra(DiaryDB.ID,0);
        mSql.delete(DiaryDB.TABLE_NAME," _id = " + id,null);
        finish();

    }
    public void goBack(View v) {
        finish();
    }
}
