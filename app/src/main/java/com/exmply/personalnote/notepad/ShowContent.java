package com.exmply.personalnote.notepad;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.exmply.personalnote.R;


public class ShowContent extends AppCompatActivity {
    private TextView mTextview;
    private TextView time;
    private NoteDB mDb;
    private SQLiteDatabase mSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏工具栏
        setContentView(R.layout.notepad_view);
        mTextview = (TextView)this.findViewById(R.id.showtext);
        time = (TextView)this.findViewById(R.id.showtime);
        mDb = new NoteDB(this);
        mSql = mDb.getWritableDatabase();
        mTextview.setText(getIntent().getStringExtra(NoteDB.CONTENT));
        time.setText(getIntent().getStringExtra(NoteDB.TIME));
    }
    public void delete(View v) {
        int id = getIntent().getIntExtra(NoteDB.ID,0);
        mSql.delete(NoteDB.TABLE_NAME," _id = " + id,null);
        finish();

    }
    public void goBack(View v) {
        finish();
    }
}
