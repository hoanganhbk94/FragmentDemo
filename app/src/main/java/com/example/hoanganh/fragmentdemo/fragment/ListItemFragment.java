package com.example.hoanganh.fragmentdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.Utils.PersonArrays;
import com.example.hoanganh.fragmentdemo.adapter.PersonAdapter;

/**
 * Created by HoangAnh on 3/20/2016.
 */
public class ListItemFragment extends Fragment {

    ListView listView;
    PersonAdapter adapter;
    Button btnNext;

    OnNextListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnNextListener {
        /**
         * Called by ListItemFragment when a press button NEXT
         */
        public void onPersonNext(Bundle bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_1, container, false);

        listView = (ListView) rootView.findViewById(R.id.listPerson);

        adapter = new PersonAdapter(getActivity(),
                R.layout.custom_listview, PersonArrays.personList);
        listView.setAdapter(adapter);


        btnNext = (Button) rootView.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putBooleanArray("AAA", adapter.itemChecked);

                mCallback.onPersonNext(bundle);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.fragment1) != null) {

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnNextListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnNextListener");
        }
    }

}
