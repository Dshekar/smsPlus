package com.example.rajashekarreddy.sample1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProdList extends BaseAdapter {

    private Context mContext;
    private List<finsms> mProductList;

    public ProdList(Context mContext, List<finsms> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=View.inflate(mContext,R.layout.item_product_list,null);
        TextView tvName=v.findViewById(R.id.contact);
        TextView tvPrice=v.findViewById(R.id.date);
        TextView tvDescription=(v.findViewById(R.id.msg));

        tvName.setText(mProductList.get(position).getContact());
        tvPrice.setText(String.valueOf(mProductList.get(position).getDate()));
        tvDescription.setText(mProductList.get(position).getMsg());
        v.setTag(mProductList.get(position).getId());


        return v;
    }
}
