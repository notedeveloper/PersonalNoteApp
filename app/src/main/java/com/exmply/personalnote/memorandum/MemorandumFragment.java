package com.exmply.personalnote.memorandum;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import android.app.AlarmManager;

import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;

import android.content.Intent;

import android.widget.TimePicker;
import android.widget.Toast;


import com.exmply.personalnote.R;

import java.util.Calendar;

public class MemorandumFragment extends Fragment {
    public MemorandumFragment(){

    }
    private EditText et;
    private Button btn;
    private TextView tv;
    private int count;
    private StringBuilder sb;
    private String msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_memorandum,container,false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.layout.activity_main);
//        getActivity().setContentView(R.layout.fragment_memorandum);

        sb = new StringBuilder();
        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle != null){
            new AlertDialog.Builder(getActivity())
                    .setIcon(null)
                    .setTitle("温馨提示")
                    .setMessage("备忘录时间到了,请注意")
                    .setPositiveButton("关闭", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }
                    })
                    .create().show();
        }
        InitViews();
    }

    private void InitViews() {
        et = (EditText) getActivity().findViewById(R.id.et);
        btn = (Button) getActivity().findViewById(R.id.alarmBtn);
        tv = (TextView) getActivity().findViewById(R.id.memo_show);

        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());//设置当前时间为c的时间
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                msg = et.getText().toString().trim();
                sb.append(count++);
                sb.append("备忘录的内容为:");
                sb.append(msg);
                sb.append("\n");
                tv.setText(sb.toString().trim());
                new TimePickerDialog(
                        getActivity(),
                        new TimePickerDialog.OnTimeSetListener(){

                            @Override
                            public void onTimeSet(TimePicker view,int hourOfDay, int minute) {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                c.set(Calendar.MILLISECOND, 0);
                                AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                                Intent intent = new Intent(getActivity(),AlarmReceive.class);
                                PendingIntent pi = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
                                am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);

                                String tempHour = (hour+"").length()>1?hour+"":"0"+hour;
                                String tempMinute = (minute+"").length()>1?minute+"":"0"+minute;
                                Toast.makeText(getActivity(), "设置时间为:"+tempHour+":"+tempMinute, Toast.LENGTH_SHORT).show();
                            }
                        },
                        hour,
                        minute,
                        true).show();
            }
        });
    }
}
