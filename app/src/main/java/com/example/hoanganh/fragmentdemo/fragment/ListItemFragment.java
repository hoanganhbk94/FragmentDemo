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
import com.example.hoanganh.fragmentdemo.adapter.PersonAdapter;
import com.example.hoanganh.fragmentdemo.utils.PersonArrays;

/**
 * Created by HoangAnh on 3/20/2016.
 */
public class ListItemFragment extends Fragment {
    public static final String LIST_ITEM_SELECTED = "LIST_ITEM_SELECTED";

    ListView listView;
    public PersonAdapter adapter;
    Button btnNext;

    boolean flag = true;

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

        if (flag) {
        adapter = new PersonAdapter(getActivity(),
                R.layout.custom_listview, PersonArrays.personList);
        }

        listView.setAdapter(adapter);

        btnNext = (Button) rootView.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putBooleanArray("AAA", adapter.getItemChecked());

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
            adapter = new PersonAdapter(getActivity(),
                    R.layout.custom_listview, PersonArrays.personList);

            adapter.setItemChecked(args.getBooleanArray(LIST_ITEM_SELECTED));

            flag = false;
        }

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
