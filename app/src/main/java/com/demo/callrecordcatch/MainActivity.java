package com.demo.callrecordcatch;

import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Button catch_call_record;
    private static List<CallLogInfo> callLogList = new ArrayList<CallLogInfo>();
    private ListView listView;
    private CallLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new CallLogAdapter(callLogList,this);
        listView.setAdapter(adapter);
        catch_call_record = (Button) findViewById(R.id.catch_call_record);
        catch_call_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catchCallData();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void catchCallData() {

        String callLogName;
        String callLogNumber;
        String callLogDate;
        int callLogType;
        int callLogTime;
        String type;
        String time;

        callLogList.clear();
        Uri callLogUri = CallLog.Calls.CONTENT_URI;
        String[] projection = {CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE, CallLog.Calls.DURATION, CallLog.Calls.DATE};
        Cursor cursor = null;
        try{
             cursor = MyApplication.getContext().getContentResolver().query(callLogUri, projection, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        }catch (Exception e){

        }
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                callLogName = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                if (callLogName==null){
                    callLogName = "未知号码";
                }
                callLogNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                callLogDate = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date d = new Date(Long.parseLong(callLogDate));
                callLogDate = DateFormat.format(d);

                callLogType =cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                if (callLogType == 1){
                    type ="来电";
                }else if (callLogType ==2){
                    type = "拨出";
                }else
                    type = "未接";

                callLogTime = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
                if (type =="未接"){
                    time ="未接";
                }else {
                    time = timeChange(callLogTime);
                }

                CallLogInfo callLogInfo = new CallLogInfo(callLogName,callLogNumber
                        ,callLogDate,callLogType,time);
                callLogList.add(callLogInfo);
                Log.d("输出",callLogDate + callLogName + callLogNumber + callLogTime + callLogType);
            }
        }else{
            Toast.makeText(this,"请检查通话记录是否授权!",Toast.LENGTH_SHORT).show();
        }


        cursor.close();
    }

    private static String timeChange(int time){
        int h = 0;
        int m = 0;
        int s = 0;
        int temp = time % 3600;
        if (time >3600){
            h = time/3600;
            if(temp!=0){
                if (temp>60){
                    m = temp/60;
                    if (temp%60!= 0 ){
                        s = temp/60;
                    }
                }else {
                    s = temp;
                }
            }
        }else {
            m = time/60;
            if(time%60!=0){
                s = time%60;
            }
        }
        return "通话时长："+ h +"时" + m +"分" + s + "秒";
    }

      @Override
        public void onBackPressed() {   //清除缓存
            callLogList.clear();
            super.onBackPressed();
        }
}
