package com.example.hoanganh.fragmentdemo.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.adapter.SelectedPersonAdapter;
import com.example.hoanganh.fragmentdemo.entity.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangAnh on 3/20/2016.
 */
public class CheckedItemFragment extends Fragment {
    private static final int CONTAINER = R.id.fragment_container;
    private static final String LIST_ITEM_SELECTED = "LIST_ITEM_SELECTED";

    private ListView listView;
    private List<Person> selectedList = new ArrayList<>();

    public static CheckedItemFragment newInstance(List<Person> selectedList) {
        Bundle args = new Bundle();
        args.putSerializable(LIST_ITEM_SELECTED, (Serializable) selectedList);
        CheckedItemFragment fragment = new CheckedItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // Get data from bundle
        if (bundle != null) {
            getDataFromBundle(bundle);
        } else {
            getDataFromBundle(getArguments());
        }
    }

    private void getDataFromBundle(Bundle bundle) {
        if (bundle == null) return;

        if (bundle.getSerializable(LIST_ITEM_SELECTED) != null)
            selectedList = (List<Person>) bundle.getSerializable(LIST_ITEM_SELECTED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.listView2);
        final SelectedPersonAdapter adapter = new SelectedPersonAdapter(getActivity(), R.layout.fragment_2, selectedList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO check null
                DetailFragment fragment = DetailFragment.newInstance((Person) adapterView.getItemAtPosition(i));
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(CONTAINER, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(LIST_ITEM_SELECTED, (Serializable) selectedList);
    }
}
