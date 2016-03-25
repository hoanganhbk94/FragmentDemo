package com.example.hoanganh.fragmentdemo.fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.entity.Person;

/**
 * Created by HoangAnh on 3/20/2016.
 */
public class DetailFragment extends Fragment {
    public static final String PERSON_SELECTED_1 = "PERSON_SELECTED_1";

    TextView txtName, txtBirthDay, txtSex;
    Person p;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_3, container, false);

        txtName = (TextView) rootView.findViewById(R.id.txtName);
        txtName.setText(p.getName());
        txtBirthDay = (TextView) rootView.findViewById(R.id.txtBirthDay);
        txtBirthDay.setText(p.getBirthDay());
        txtSex = (TextView) rootView.findViewById(R.id.txtSex);
        txtSex.setText(p.isSex() ? "Male" : "Female");

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            p = (Person) args.get(PERSON_SELECTED_1);
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        // Use Shared Preferences to save data
        SharedPreferences previewSizePref = getActivity().getSharedPreferences("PREF", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = previewSizePref.edit();

        prefEditor.putString("name", p.getName());
        prefEditor.putString("day", p.getBirthDay());
        prefEditor.putBoolean("sex", p.isSex());

        prefEditor.commit();

    }
}
