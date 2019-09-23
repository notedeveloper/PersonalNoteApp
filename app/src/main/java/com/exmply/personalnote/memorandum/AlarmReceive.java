package com.exmply.personalnote.memorandum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmReceive extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent tempIntent = new Intent(context,getClass());
        Bundle bundle = new Bundle();
        bundle.putString("msg", "msg");
        tempIntent.putExtras(bundle);
        tempIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//设置新的task
        context.startActivity(tempIntent);
    }
}
