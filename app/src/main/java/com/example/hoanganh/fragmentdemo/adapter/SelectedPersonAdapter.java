package com.example.hoanganh.fragmentdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.entity.Person;

import java.util.List;

/**
 * Created by HoangAnh on 3/22/2016.
 */
public class SelectedPersonAdapter extends ArrayAdapter<Person> {
    public SelectedPersonAdapter(Context context, int resource) {
        super(context, resource);
    }

    public SelectedPersonAdapter(Context context, int resource, List<Person> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_selected_listview, null);
        }

        Person p = getItem(position);

        if (p != null) {
            // Anh xa + Gan gia tri
            TextView textView = (TextView) v.findViewById(R.id.name2);
            textView.setText(p.getName());
        }

        return v;
    }
}
