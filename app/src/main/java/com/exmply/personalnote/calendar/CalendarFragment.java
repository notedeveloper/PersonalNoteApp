package com.exmply.personalnote.calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exmply.personalnote.R;
import com.exmply.personalnote.calendar.model.CalendarCard;
import com.exmply.personalnote.calendar.model.CalendarViewAdapter;
import com.exmply.personalnote.calendar.model.Custom;
import com.exmply.personalnote.calendar.model.CustomDate;
import com.exmply.personalnote.calendar.model.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment implements CalendarCard.OnCellClickListener{
    public CalendarFragment(){

    }
    private ViewPager mViewPager;
    private TextView monthText;
    private int mCurrentIndex = 498;
    private CalendarViewAdapter<CalendarCard> adapter;
    private List<Custom> listDay;
    private CalendarCard[] views;

    private LinearLayout indicatorLayout;
    enum SildeDirection {
        RIGHT, LEFT, NO_SILDE
    }
    private SildeDirection mDirection = SildeDirection.NO_SILDE;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View views =  inflater.inflate(R.layout.fragment_calendar,container,false);

        return views;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.vp_calendar);
        monthText = (TextView) getActivity().findViewById(R.id.tvCurrentMonth);
        indicatorLayout = (LinearLayout) getActivity().findViewById(R.id.layout_drop);
        int month = DateUtil.getCurrentMonthNow();
        int year = DateUtil.getCurrentYeatNow();
        CustomDate c = new CustomDate(year, month, 1);
        monthText.setText(showTimeCount(c));
        initData();
    }

    public String showTimeCount(CustomDate time) {
        String timeCount;
        long minuec = time.month;
        String minue = "0" + minuec;
        minue = minue.substring(minue.length() - 2, minue.length());
        long secc = time.day;
        String sec = "0" + secc;
        sec = sec.substring(sec.length() - 2, sec.length());
        timeCount = time.year + "年" + minue + "月";
        return timeCount;
    }

    private void initData() {
        listDay = new ArrayList<>();
        for (int i = 1;i<5;i++){
            Custom custom = new Custom(2017,7,i);
            listDay.add(custom);
        }
        for (int i = 1;i<5;i++){
            Custom custom = new Custom(2017,7+i,i);
            listDay.add(custom);
        }
        views = new CalendarCard[6];
        for (int i = 0; i < 6; i++) {
            views[i] = new CalendarCard(this.getActivity(), this, listDay);
        }
        adapter = new CalendarViewAdapter<>(views);
        setViewPager();
        CustomDate c = new CustomDate(DateUtil.getCurrentYeatNow(), DateUtil.getCurrentMonthNow(), DateUtil.getCurrentMonthDay());
    }
    private void setViewPager() {
        mViewPager.setAdapter(adapter);
//        initCategoryBarPoint(indicatorLayout);
        mViewPager.setCurrentItem(mCurrentIndex);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                measureDirection(position);
                updateCalendarView(position);
//                setIndicator(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
    /* 计算方向
     *
     */
    private void measureDirection(int arg0) {

        if (arg0 > mCurrentIndex) {
            mDirection = SildeDirection.RIGHT;

        } else if (arg0 < mCurrentIndex) {
            mDirection = SildeDirection.LEFT;
        }
        mCurrentIndex = arg0;
    }
    // 更新日历视图
    private void updateCalendarView(int arg0) {
        CustomDate customDate = new CustomDate();
        CalendarCard[] mShowViews = adapter.getAllItems();
        if (mDirection == SildeDirection.RIGHT) {
            customDate = mShowViews[arg0 % mShowViews.length].rightSlide();
        } else if (mDirection == SildeDirection.LEFT) {
            customDate = mShowViews[arg0 % mShowViews.length].leftSlide();
        }
        mDirection = SildeDirection.NO_SILDE;
        if (customDate != null) {
            monthText.setText(showTimeCount(customDate));
            //进行网络请求

        }


    }
    @Override
    public void clickDate(CustomDate date) {
        Toast.makeText(this.getActivity(),showTimeCountAll(date),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeDate(CustomDate date) {

    }

    public String showTimeCountAll(CustomDate time) {
        String timeCount;
        long minuec = time.month;
        String minue = "0" + minuec;
        minue = minue.substring(minue.length() - 2, minue.length());
        long secc = time.day;
        String sec = "0" + secc;
        sec = sec.substring(sec.length() - 2, sec.length());
        timeCount = time.year + minue + sec;
        return timeCount;
    }

}
