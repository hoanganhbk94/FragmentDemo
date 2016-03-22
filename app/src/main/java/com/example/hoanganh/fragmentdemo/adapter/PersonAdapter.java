package com.example.hoanganh.fragmentdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangAnh on 3/21/2016.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    public boolean[] itemChecked;
    List<Person> items = new ArrayList<>();
    Context context;

    public PersonAdapter(Context context, int resource) {
        super(context, resource);
    }

    public PersonAdapter(Context context, int resource, List<Person> items) {
        super(context, resource, items);

        this.items = items;
        this.context = context;

        itemChecked = new boolean[items.size()];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        LayoutInflater inflater = LayoutInflater.from(getContext());

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_listview, null);
            holder = new ViewHolder();

            holder.apkName = (TextView) convertView
                    .findViewById(R.id.name1);
            holder.ck1 = (CheckBox) convertView
                    .findViewById(R.id.checkBox);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Person p = getItem(position);

        if (p != null) {
            holder.apkName.setText(p.getName());
            if (itemChecked[position])
                holder.ck1.setChecked(true);
            else
                holder.ck1.setChecked(false);
        }

        holder.ck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (holder.ck1.isChecked())
                    itemChecked[position] = true;
                else
                    itemChecked[position] = false;
            }
        });

        return convertView;

    }

    private class ViewHolder {
        TextView apkName;
        CheckBox ck1;
    }

    public int getCount() {
        return items.size();
    }

    public Person getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }


}
