package com.exmply.personalnote.calendar.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalendarCard extends View {

    private static final int TOTAL_COL = 7;
    private static final int TOTAL_ROW = 6;

    private Paint mCirclePaint;
    private Paint mCirclePaintKong;
    private Paint mTextPaint;
    private Paint mDianPaint;
    private int mViewWidth;
    private int mViewHeight;
    private int mCellSpace;
    private int mW;
    private int mH;
    private Row rows[] = new Row[TOTAL_ROW];
    private static CustomDate mShowDate; // year,month,day
    private OnCellClickListener mCellClickListener; //
    private int touchSlop; //
    private boolean callBackCellSpace;

    private Cell mClickCell;
    private float mDownX;
    private float mDownY;
    private List<Custom> listDay;
    private SimpleDateFormat sdfLast = new SimpleDateFormat("yyyy-M-d");

    private CustomDate customNow;

    /**
     *
     *
     *
     */
    public interface OnCellClickListener {
        void clickDate(CustomDate date);

        void changeDate(CustomDate date);
    }

//    public CalendarCard(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(context);
//    }

    public CalendarCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarCard(Context context) {
        super(context);
        init(context);
    }

    public CalendarCard(Context context, OnCellClickListener listener, List<Custom> listDay) {
        super(context);
        this.mCellClickListener = listener;
        this.listDay = listDay;
        init(context);
    }

    public void updateList(List<Custom> listDay) {
        this.listDay = listDay;
        update();
    }

    private void init(Context context) {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaintKong = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDianPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaintKong.setStyle(Paint.Style.STROKE);
        mCirclePaintKong.setStrokeWidth(3);
        mDianPaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.parseColor("#ff7800"));
        mCirclePaintKong.setColor(Color.parseColor("#ff7800"));
        mDianPaint.setColor(Color.GRAY);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initDate();
    }

    private void initDate() {
        customNow = new CustomDate(0, 0, 0);
        mShowDate = new CustomDate();
        fillDate();

    }

    private void fillDate() {
        int monthDay = DateUtil.getCurrentMonthDay();
        int month = DateUtil.getCurrentMonthNow();
        int year = DateUtil.getCurrentYeatNow();

        int yearClick;
        int monthClick;
        int dayClick;

        int lastMonthDays = DateUtil.getMonthDays(mShowDate.year,
                mShowDate.month - 1);
        int currentMonthDays = DateUtil.getMonthDays(mShowDate.year,
                mShowDate.month);
        int firstDayWeek = DateUtil.getWeekDayFromDate(mShowDate.year,
                mShowDate.month);
        int day = 0;
        for (int j = 0; j < TOTAL_ROW; j++) {
            rows[j] = new Row(j);
            for (int i = 0; i < TOTAL_COL; i++) {
                int position = i + j * TOTAL_COL;
                int have = 0;

                if (position >= firstDayWeek && position < firstDayWeek + currentMonthDays) {
                    day++;
                    yearClick = mShowDate.year;
                    monthClick = mShowDate.month;
                    dayClick = day;
                    rows[j].cells[i] = new Cell(new CustomDate(yearClick, monthClick, dayClick), State.CURRENT_MONTH_DAY, have, i, j);
                    for (int n = 0; n < listDay.size(); n++) {
                        if (mShowDate.getYear() == listDay.get(n).getYear() && mShowDate.getMonth() == listDay.get(n).getMonth() && day == listDay.get(n).getDay()) {
                            rows[j].cells[i] = new Cell(new CustomDate(yearClick, monthClick, dayClick), State.PITCH_ON_DAY, have, i, j);
                            break;
                        }
                    }

                    try {
                        String time = year + "-" + month + "-" + monthDay;
                        String timeNow = yearClick + "-" + monthClick + "-" + dayClick;
                        Date timeDate = sdfLast.parse(time);
                        Date timeNowDate = sdfLast.parse(timeNow);
                        long timeLong = timeDate.getTime();
                        long timeNowLong = timeNowDate.getTime();
                        if (timeNowLong == timeLong) {
                            rows[j].cells[i] = new Cell(new CustomDate(yearClick, monthClick, dayClick), State.TODAY, have, i, j);
                        }
                    } catch (ParseException e) {

                    }
                } else if (position < firstDayWeek) {
                    yearClick = mShowDate.year;
                    monthClick = mShowDate.month - 1;
                    dayClick = lastMonthDays - (firstDayWeek - position - 1);
                    rows[j].cells[i] = new Cell(new CustomDate(yearClick, monthClick, dayClick), State.NO_CURRENT_MONTH_DAY, have, i, j);
                } else if (position >= firstDayWeek + currentMonthDays) {
                    yearClick = mShowDate.year;
                    monthClick = mShowDate.month + 1;
                    dayClick = position - firstDayWeek - currentMonthDays + 1;
                    rows[j].cells[i] = new Cell(new CustomDate(yearClick, monthClick, dayClick), State.NO_CURRENT_MONTH_DAY, have, i, j);
                }


            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < TOTAL_ROW; i++) {
            if (rows[i] != null) {
                rows[i].drawCells(canvas);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mCellSpace = Math.min(mViewHeight / TOTAL_ROW, mViewWidth / TOTAL_COL);
        mW = mViewWidth / TOTAL_COL;
        mH = mViewHeight / TOTAL_ROW;
        if (!callBackCellSpace) {
            callBackCellSpace = true;
        }
        mTextPaint.setTextSize(mW / 3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float disX = event.getX() - mDownX;
                float disY = event.getY() - mDownY;
                if (Math.abs(disX) < touchSlop && Math.abs(disY) < touchSlop) {
                    int col = (int) (mDownX / mW);
                    int row = (int) (mDownY / mH);
                    measureClickCell(col, row);
                }
                break;
            default:
                break;
        }

        return true;
    }

    /**
     *
     *
     *
     *
     */
    private void measureClickCell(int col, int row) {
        if (col >= TOTAL_COL || row >= TOTAL_ROW)
            return;
        if (mClickCell != null) {
            rows[mClickCell.j].cells[mClickCell.i] = mClickCell;
        }
        if (rows[row] != null) {
            mClickCell = new Cell(rows[row].cells[col].date,
                    rows[row].cells[col].state, rows[row].cells[col].i,
                    rows[row].cells[col].j);

            CustomDate date = rows[row].cells[col].date;
            date.week = col;
            int monthDay = DateUtil.getCurrentMonthDay();
            int monthNow = DateUtil.getCurrentMonthNow();
            int yearNow = DateUtil.getCurrentYeatNow();
            if (date.getYear() == yearNow && date.getMonth() == monthNow && date.getDay() == monthDay) {
                mCellClickListener.clickDate(date);
            }
            for (int n = 0; n < listDay.size(); n++) {
                if (date.getYear() == listDay.get(n).getYear() && date.getMonth() == listDay.get(n).getMonth() && date.getDay() == listDay.get(n).getDay()) {
                    mCellClickListener.clickDate(date);
                    break;
                }
            }
        }
    }


    public static CustomDate setCustomDate(CustomDate chage, Custom now) {
        chage.setYear(now.getYear());
        chage.setMonth(now.getMonth());
        chage.setDay(now.getDay());
        return chage;
    }

    /**
     *
     *
     *
     */
    class Row {
        public int j;

        Row(int j) {
            this.j = j;
        }

        public Cell[] cells = new Cell[TOTAL_COL];


        public void drawCells(Canvas canvas) {
            for (int i = 0; i < cells.length; i++) {
                if (cells[i] != null) {
                    cells[i].drawSelf(canvas);
                }
            }
        }

    }

    /**
     *
     *
     *
     */
    class Cell {
        public CustomDate date;
        public State state;
        public int have;
        public int i;
        public int j;

        public Cell(CustomDate date, State state, int i, int j) {
            super();
            this.date = date;
            this.state = state;
            this.i = i;
            this.j = j;
        }

        public Cell(CustomDate date, State state, int have, int i, int j) {
            super();
            this.date = date;
            this.state = state;
            this.have = have;
            this.i = i;
            this.j = j;
        }


        public void drawSelf(Canvas canvas) {
            switch (state) {
                case CURRENT_MONTH_DAY:
                    mTextPaint.setColor(Color.parseColor("#484848"));
                    break;
                case TODAY:
                    mTextPaint.setColor(Color.parseColor("#ff7800"));
                    canvas.drawCircle((float) (mW * (i + 0.51)),
                            (float) ((j + 0.38) * mH), mCellSpace * 5 / 13,
                            mCirclePaintKong);
                    break;
                case PITCH_ON_DAY:
                    mTextPaint.setColor(Color.parseColor("#fffffe"));
                    canvas.drawCircle((float) (mW * (i + 0.51)),
                            (float) ((j + 0.38) * mH), mCellSpace * 5 / 13,
                            mCirclePaint);
                    break;
                case NO_CURRENT_MONTH_DAY:
                    mTextPaint.setColor(Color.WHITE);
                    break;

                default:
                    break;
            }

            String content = date.day + "";
            canvas.drawText(content, (float) ((i + 0.5) * mW - mTextPaint.measureText(content) / 2), (float) ((j + 0.7) * mH - mTextPaint.measureText(content, 0, 1) / 2), mTextPaint);
        }
    }

    /**
     *
     */
    enum State {
        TODAY, NO_CURRENT_MONTH_DAY, CURRENT_MONTH_DAY, PITCH_ON_DAY
    }


    public CustomDate leftSlide() {
        if (mShowDate.month == 1) {
            mShowDate.month = 12;
            mShowDate.year -= 1;
        } else {
            mShowDate.month -= 1;
        }
        update();
        return mShowDate;
    }


    public CustomDate rightSlide() {
        if (mShowDate.month == 12) {
            mShowDate.month = 1;
            mShowDate.year += 1;
        } else {
            mShowDate.month += 1;
        }

        update();
        return mShowDate;
    }

    public void update() {
        fillDate();
        invalidate();
    }

}

