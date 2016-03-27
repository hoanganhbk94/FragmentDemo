package com.example.hoanganh.fragmentdemo.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.adapter.PersonAdapter;
import com.example.hoanganh.fragmentdemo.entity.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangAnh on 3/20/2016.
 */
public class ListItemFragment extends Fragment {
    private static final int CONTAINER = R.id.fragment_container;
    private static final String PERSON_LIST = "PERSON_LIST";
    private static final String LIST_ITEM_SELECTED = "LIST_ITEM_SELECTED";

    private ListView listView;
    private PersonAdapter adapter;
    private List<Person> mPersonList;
    private boolean[] mPersonSelected;

    public static ListItemFragment newInstance(List<Person> personList) {
        Bundle args = new Bundle();
        args.putSerializable(PERSON_LIST, (Serializable) personList);
        ListItemFragment fragment = new ListItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            getDataFromBundle(bundle);
        } else {
            getDataFromBundle(getArguments());
        }
    }

    private void getDataFromBundle(Bundle bundle) {
        if (bundle == null) return;
        mPersonList = (ArrayList) bundle.getSerializable(PERSON_LIST);

        if (bundle.getBooleanArray(LIST_ITEM_SELECTED) != null) {
            mPersonSelected = bundle.getBooleanArray(LIST_ITEM_SELECTED);
        } else {
            mPersonSelected = new boolean[mPersonList.size()];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("AAA", "ListItem Fragment onViewCreated()");

        listView = (ListView) view.findViewById(R.id.listPerson);

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO next fragment
                List<Person> sendList = new ArrayList<Person>();
                for (int i = 0; i < mPersonSelected.length; i++) {
                    if (mPersonSelected[i] == true) {
                        sendList.add(mPersonList.get(i));
                    }
                }

                nextCheckedItemFragment(sendList);
            }
        });
    }

    private void nextCheckedItemFragment(List<Person> sendList) {

        CheckedItemFragment fragment = CheckedItemFragment.newInstance(sendList);

        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        fragmentTransaction.replace(CONTAINER, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPersonList != null) {
            adapter = new PersonAdapter(getActivity(),
                    R.layout.custom_listview, mPersonList, mPersonSelected);
        }
        listView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PERSON_LIST, (Serializable) mPersonList);
        outState.putBooleanArray(LIST_ITEM_SELECTED, mPersonSelected);
    }
}
