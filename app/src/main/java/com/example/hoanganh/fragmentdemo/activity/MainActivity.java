package com.example.hoanganh.fragmentdemo.activity;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.entity.Person;
import com.example.hoanganh.fragmentdemo.fragment.CheckedItemFragment;
import com.example.hoanganh.fragmentdemo.fragment.DetailFragment;
import com.example.hoanganh.fragmentdemo.fragment.ListItemFragment;

public class MainActivity extends FragmentActivity implements ListItemFragment.OnNextListener,
        CheckedItemFragment.OnClcikItemListener {

    public static final String LIST_ITEM_SELECTED = "LIST_ITEM_SELECTED";
    public static final String PERSON_SELECTED = "PERSON_SELECTED";
    public static final String PERSON_SELECTED_1 = "PERSON_SELECTED_1";
    ListItemFragment firstFragment;

    State state;

    public enum State {
        STATE_1,
        STATE_2,
        STATE_3
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        state = State.STATE_1;

        firstFragment = new ListItemFragment();

        // Get data from onSaveInstanceState
        if (savedInstanceState != null) {
            firstFragment.setArguments(savedInstanceState);
        }

        getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.clear();
        savedInstanceState.putBooleanArray(LIST_ITEM_SELECTED, firstFragment.adapter.getItemChecked());
    }

    @Override
    public void onPersonNext(Bundle bundle) {
        state = State.STATE_2;

        CheckedItemFragment newFragment = new CheckedItemFragment();
        newFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

    @Override
    public void onClickItemListenner(Bundle bundle) {
        state = State.STATE_3;

        DetailFragment newFragment = new DetailFragment();
        newFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    // Override back button
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            state = State.STATE_1;
            this.finish();
        } else {
            if(state == State.STATE_3){
                state = State.STATE_2;
            } else if(state == State.STATE_2){
                state = State.STATE_1;
            }
            getFragmentManager().popBackStack();
        }
        //Toast.makeText(this, state.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Use Shared Preferences to save data
        SharedPreferences previewSizePref = getSharedPreferences("PREF", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = previewSizePref.edit();

        prefEditor.putString("state", state.toString());

        prefEditor.commit();

        //Toast.makeText(this, "Activity onPause", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences previewSizePref = getSharedPreferences("PREF", MODE_PRIVATE);

        if (previewSizePref.contains("state")) {
            //Toast.makeText(this, previewSizePref.getString("state", null), Toast.LENGTH_SHORT).show();

            String state = previewSizePref.getString("state", null);
            if (state.equals(State.STATE_1.toString())) {
                // Don't nothing
            } else if (state.equals(State.STATE_2.toString())) {
                Bundle bundle = new Bundle();

                int lenng_arr = previewSizePref.getInt("ArrayLength", 0);

                boolean[] a = new boolean[lenng_arr];
                for (int i = 0; i < lenng_arr; i++) {
                    if (previewSizePref.getInt(String.valueOf(i), 10000) == i) {
                        a[i] = true;
                    } else {
                        a[i] = false;
                    }
                }

                bundle.putBooleanArray(PERSON_SELECTED, a);

                this.onPersonNext(bundle);
            } else if (state.equals(State.STATE_3.toString())) {
                Bundle bundle = new Bundle();

                int lenng_arr = previewSizePref.getInt("ArrayLength", 0);

                boolean[] a = new boolean[lenng_arr];
                for (int i = 0; i < lenng_arr; i++) {
                    if (previewSizePref.getInt(String.valueOf(i), 10000) == i) {
                        a[i] = true;
                    } else {
                        a[i] = false;
                    }
                }

                bundle.putBooleanArray(PERSON_SELECTED, a);

                this.onPersonNext(bundle);


                //Bundle bundle = new Bundle();

                String name = previewSizePref.getString("name", null);
                String day = previewSizePref.getString("day", null);
                boolean sex = previewSizePref.getBoolean("sex", true);

                Person p = new Person(name, day, sex);
                bundle.putSerializable(PERSON_SELECTED_1, p);

                this.onClickItemListenner(bundle);
            }
        }


        this.getSharedPreferences("PREF", 0).edit().clear().commit();
    }


}
