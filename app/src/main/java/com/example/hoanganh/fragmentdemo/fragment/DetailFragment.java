package com.example.hoanganh.fragmentdemo.fragment;

import android.app.Fragment;
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
    private static final String PERSON_SELECTED = "PERSON_SELECTED";

    private TextView txtName, txtBirthDay, txtSex;
    private Person person;

    public static DetailFragment newInstance(Person person) {
        
        Bundle args = new Bundle();
        args.putSerializable(PERSON_SELECTED, person);
        
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            getDataFromBundle(savedInstanceState);
        } else {
            getDataFromBundle(getArguments());
        }
    }

    private void getDataFromBundle(Bundle bundle) {
        if(bundle == null) return;
        person = (Person) bundle.getSerializable(PERSON_SELECTED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtName = (TextView) view.findViewById(R.id.txtName);
        txtName.setText(person.getName());
        txtBirthDay = (TextView) view.findViewById(R.id.txtBirthDay);
        txtBirthDay.setText(person.getBirthDay());
        txtSex = (TextView) view.findViewById(R.id.txtSex);
        txtSex.setText(person.isSex() ? "Male" : "Female");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(PERSON_SELECTED, person);
    }
}
