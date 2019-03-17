package com.exmply.personalnote;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.exmply.personalnote.calendar.CalendarFragment;

import com.exmply.personalnote.diary.DiaryFragment;
import com.exmply.personalnote.memorandum.MemorandumFragment;
import com.exmply.personalnote.notepad.NotepadFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title,item_calendar,item_diary,item_memorandum,item_notepad;
    private ViewPager viewPager;

    private CalendarFragment calendarFragment;
    private DiaryFragment diaryFragment;
    private MemorandumFragment memorandumFragment;
    private NotepadFragment notepadFragment;

    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentAdapter FragmentAdapter;

    String[] titles = new String[] {"日历","日记本","记事本","备忘录"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏工具栏
        setContentView(R.layout.activity_main);

        initViews();

        FragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(),fragmentList);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(FragmentAdapter);
        viewPager.setCurrentItem(0);
        item_calendar.setTextColor(Color.parseColor("#66CDAA"));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //在页面被选中时候调用此方法

                title.setText(titles[position]);
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /**
                 * 在状态改变时候调用此方法
                 * 1：正在滑动
                 * 2：滑动完毕
                 * 0：什么都没做
                 */
            }
        });
    }

    private void initViews() {
        title = (TextView)findViewById(R.id.title);
        item_calendar = (TextView)findViewById(R.id.item_calendar);
        item_diary = (TextView)findViewById(R.id.item_diary);
        item_notepad = (TextView)findViewById(R.id.item_notepad);
        item_memorandum = (TextView)findViewById(R.id.item_memorandum);

        //为每个帧页面设置监听
        item_calendar.setOnClickListener(this);
        item_diary.setOnClickListener(this);
        item_notepad.setOnClickListener(this);
        item_memorandum.setOnClickListener(this);


        viewPager = (ViewPager)findViewById(R.id.mainView);
        calendarFragment = new CalendarFragment();
        diaryFragment = new DiaryFragment();
        notepadFragment = new NotepadFragment();
        memorandumFragment = new MemorandumFragment();

        fragmentList.add(calendarFragment);
        fragmentList.add(diaryFragment);
        fragmentList.add(notepadFragment);
        fragmentList.add(memorandumFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_calendar:
                viewPager.setCurrentItem(0,true);
                break;
            case R.id.item_diary:
                viewPager.setCurrentItem(1,true);
                break;
            case R.id.item_notepad:
                viewPager.setCurrentItem(2,true);
                break;
            case R.id.item_memorandum:
                viewPager.setCurrentItem(3,true);
                break;
        }
    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList; //可能不用“ = new ArrayList<Fragment>()”
        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList){
            super(fm);
            this.fragmentList = fragmentList;
        }
        @Override
        public Fragment getItem(int position){
            return fragmentList.get(position);
        }
        @Override
        public int getCount(){
            return fragmentList.size();
        }
    }
    /**
     * 由ViewPager的滑动修改底部的导航Text的颜色
     * @param position  获知底部位置position
     */
    private void changeTextColor(int position){
        if(position == 0){
            item_calendar.setTextColor(Color.parseColor("#66CDAA"));
            item_diary.setTextColor(Color.parseColor("#AAAAAA"));
            item_notepad.setTextColor(Color.parseColor("#AAAAAA"));
            item_memorandum.setTextColor(Color.parseColor("#AAAAAA"));
        }
        else if(position == 1){
            item_calendar.setTextColor(Color.parseColor("#AAAAAA"));
            item_diary.setTextColor(Color.parseColor("#66CDAA"));
            item_notepad.setTextColor(Color.parseColor("#AAAAAA"));
            item_memorandum.setTextColor(Color.parseColor("#AAAAAA"));
        }
        else if(position == 2){
            item_calendar.setTextColor(Color.parseColor("#AAAAAA"));
            item_diary.setTextColor(Color.parseColor("#AAAAAA"));
            item_notepad.setTextColor(Color.parseColor("#66CDAA"));
            item_memorandum.setTextColor(Color.parseColor("#AAAAAA"));
        }
        else if(position == 3){
            item_calendar.setTextColor(Color.parseColor("#AAAAAA"));
            item_diary.setTextColor(Color.parseColor("#AAAAAA"));
            item_notepad.setTextColor(Color.parseColor("#AAAAAA"));
            item_memorandum.setTextColor(Color.parseColor("#66CDAA"));
        }
    }
}
