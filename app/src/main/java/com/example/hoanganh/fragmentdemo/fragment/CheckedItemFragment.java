package com.example.hoanganh.fragmentdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.utils.PersonArrays;
import com.example.hoanganh.fragmentdemo.adapter.SelectedPersonAdapter;
import com.example.hoanganh.fragmentdemo.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangAnh on 3/20/2016.
 */
public class CheckedItemFragment extends Fragment {
    public static final String PERSON_SELECTED = "PERSON_SELECTED";
    public static final String PERSON_SELECTED_1 = "PERSON_SELECTED_1";

    ListView listView;
    List<Person> selectedList = new ArrayList<>();

    OnClcikItemListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnClcikItemListener {
        /**
         * Called by ListItemFragment when a press button NEXT
         */
        public void onClickItemListenner(Bundle bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            // Get data
        }


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_2, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView2);

        SelectedPersonAdapter adapter = new SelectedPersonAdapter(getActivity(), R.layout.fragment_2, selectedList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();

                Person p = (Person) listView.getItemAtPosition(i);

                //Toast.makeText(getActivity(), p.getName(), Toast.LENGTH_SHORT).show();

                bundle.putSerializable(PERSON_SELECTED_1, p);
                mCallback.onClickItemListenner(bundle);
            }
        });

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
            if(args.getBooleanArray(PERSON_SELECTED)==null)
                return;

            boolean a[] = args.getBooleanArray(PERSON_SELECTED);
            for (int i = 0; i < a.length; i++) {
                if (a[i] == true) {
                    selectedList.add(PersonArrays.personList.get(i));
                }
            }
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnClcikItemListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnClcikItemListener");
        }
    }
}
