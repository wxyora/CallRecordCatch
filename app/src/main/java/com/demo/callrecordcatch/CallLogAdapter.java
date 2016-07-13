package com.demo.callrecordcatch;


import android.content.Context;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CallLogAdapter extends BaseAdapter {
    private List<CallLogInfo> list;
    private Context context;

    public CallLogAdapter(List<CallLogInfo> list,Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        CallLogInfo callLog = list.get(position);
        if (convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item,null);
            holder = new ViewHolder();
            //holder.call_type = (TextView) convertView.findViewById(R.id.call_type);
            holder.name = (TextView) convertView.findViewById(R.id.call_name);
            holder.number = (TextView) convertView.findViewById(R.id.call_number);
            holder.date = (TextView) convertView.findViewById(R.id.call_date);
            holder.time = (TextView) convertView.findViewById(R.id.call_time);

            setType(callLog,holder);
            holder.name.setText(callLog.getName());
            //holder.number.setText(callLog.getNumber());
            holder.date.setText(callLog.getDate());
            holder.time.setText(callLog.getTime());

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
            setType(callLog,holder);
            holder.name.setText(callLog.getName());
            //holder.number.setText(callLog.getNumber());
            holder.date.setText(callLog.getDate());
            holder.time.setText(callLog.getTime());

        }


        return convertView;
    }


    private static class ViewHolder{
        TextView call_type;
        TextView name;
        TextView number;
        TextView date;
        TextView time;
    }
    private void setType(CallLogInfo callLog, ViewHolder holder){
        switch (callLog.getType()) {
            case 1:
                //holder.call_type.setBackgroundResource(R.drawable.laidian);
                holder.number.setText("来电-"+callLog.getNumber());
                break;
            case 2:
                //holder.call_type.setBackgroundResource(R.drawable.bochu);
                holder.number.setText("拨出-"+callLog.getNumber());
                break;
            case 3:
                //holder.call_type.setBackgroundResource(R.drawable.weijie);
                holder.number.setText("未接-"+callLog.getNumber());
                break;
        }

    }

}