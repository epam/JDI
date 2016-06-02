package com.epam.jdi.jditestandroidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epam.jdi.jditestandroidapp.MainItem;

import java.util.List;

/**
 * Created by Vitalii_Sokolov on 6/2/2016.
 */
public class ItemAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private Context mContext;
    private List<MainItem> items;

    public ItemAdapter(Context mContext, List<MainItem> items) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MainItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).mId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bind(position);
        return convertView;
    }

    class ViewHolder {
        TextView mTextView;

        public ViewHolder(View view) {
            mTextView = (TextView) view.findViewById(android.R.id.text1);
        }

        public void bind(int position) {
            MainItem item = getItem(position);
            mTextView.setText(mContext.getString(item.mMenuName));
        }
    }
}
