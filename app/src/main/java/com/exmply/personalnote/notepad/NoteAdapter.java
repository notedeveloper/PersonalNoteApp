package com.exmply.personalnote.notepad;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exmply.personalnote.R;


public class NoteAdapter extends BaseAdapter {
    private Context mContext;
    private Cursor mCursor;
    private FrameLayout mLayout;
    public NoteAdapter(Context mContext,Cursor mCursor){
        this.mContext = mContext;
        this.mCursor = mCursor;
    }

    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mCursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mLayout = (FrameLayout)inflater.inflate(R.layout.notepad_listview,null);
        TextView content = (TextView) mLayout.findViewById(R.id.list_content);
        TextView time = (TextView) mLayout.findViewById(R.id.list_time);
        mCursor.moveToPosition(position);
        String dbcontext = mCursor.getString(mCursor.getColumnIndex("content"));
        String dbtime = mCursor.getString(mCursor.getColumnIndex("time"));
        content.setText(dbcontext);
        time.setText(dbtime);
        return mLayout;
    }
}
