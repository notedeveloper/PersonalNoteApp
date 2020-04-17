package com.exmply.personalnote.memorandum;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;

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
    public MemorandumFragment() {

    }

    private EditText editText;
    private Button button;
    private TextView textView;
    private int count;
    private StringBuilder stringBuilder;
    private String msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_memorandum, container, false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.layout.activity_main);
//        getActivity().setContentView(R.layout.fragment_memorandum);

        stringBuilder = new StringBuilder();
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
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
        editText = (EditText) getActivity().findViewById(R.id.et);
        button = (Button) getActivity().findViewById(R.id.alarmBtn);
        textView = (TextView) getActivity().findViewById(R.id.memo_show);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());//设置当前时间为calendar的时间
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                msg = editText.getText().toString().trim();
                stringBuilder.append(count++);
                stringBuilder.append("备忘录的内容为:");
                stringBuilder.append(msg);
                stringBuilder.append("\n");
                textView.setText(stringBuilder.toString().trim());
                new TimePickerDialog(
                        getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.setTimeInMillis(System.currentTimeMillis());
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                calendar.set(Calendar.SECOND, 0);
                                calendar.set(Calendar.MILLISECOND, 0);
                                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                                Intent intent = new Intent(getActivity(), AlarmReceive.class);
                                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
                                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                                String tempHour = (hour + "").length() > 1 ? hour + "" : "0" + hour;
                                String tempMinute = (minute + "").length() > 1 ? minute + "" : "0" + minute;
                                Toast.makeText(getActivity(), "设置时间为:" + tempHour + ":" + tempMinute, Toast.LENGTH_SHORT).show();
                            }
                        },
                        hour,
                        minute,
                        true).show();
            }
        });
    }
}
