package com.example.rajashekarreddy.sample1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;



public class MessageListAdapter extends BaseAdapter {


    private Context mContext;
    private List<Message> mMessageList;

    public MessageListAdapter(Context mContext, List<Message> mMessageList) {
        this.mContext = mContext;
        this.mMessageList = mMessageList;
    }

    @Override
    public int getCount() {
        return mMessageList.size();
    }

    @Override
    public Object getItem(int position) {
        return  mMessageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

if (mMessageList.get(position).getType().equals("1")) {
    View v = View.inflate(mContext, R.layout.row_left, null);
    TextView message=v.findViewById(R.id.msg);
    TextView date=v.findViewById(R.id.date);
    message.setText(mMessageList.get(position).getMsg());
    date.setText(String.valueOf(mMessageList.get(position).getDate()));
    v.setTag(mMessageList.get(position).getId());
    return v;
}
else {
    View v = View.inflate(mContext, R.layout.row_right, null);
    TextView message=v.findViewById(R.id.msg);
    TextView date=v.findViewById(R.id.date);
    message.setText(mMessageList.get(position).getMsg());
    date.setText(String.valueOf(mMessageList.get(position).getDate()));
    v.setTag(mMessageList.get(position).getId());
    return v;
}



    }
}
