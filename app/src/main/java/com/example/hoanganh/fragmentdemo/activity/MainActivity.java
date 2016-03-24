package com.example.hoanganh.fragmentdemo.activity;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.fragment.CheckedItemFragment;
import com.example.hoanganh.fragmentdemo.fragment.DetailFragment;
import com.example.hoanganh.fragmentdemo.fragment.ListItemFragment;

public class MainActivity extends FragmentActivity implements ListItemFragment.OnNextListener,
        CheckedItemFragment.OnClcikItemListener {

    public static final String LIST_ITEM_SELECTED = "LIST_ITEM_SELECTED";

    ListItemFragment firstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment = new ListItemFragment();

        // Get data from onSaveInstanceState
        if (savedInstanceState != null) {
            firstFragment.setArguments(savedInstanceState);
        }

        // Check screen orientation
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        } else {
            CheckedItemFragment secondFragment = new CheckedItemFragment();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment1, firstFragment);
            transaction.add(R.id.fragment2, secondFragment);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.clear();

        savedInstanceState.putBooleanArray(LIST_ITEM_SELECTED, firstFragment.adapter.getItemChecked());
    }

    @Override
    public void onPersonNext(Bundle bundle) {

        // Check orientation
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            CheckedItemFragment newFragment = new CheckedItemFragment();
            newFragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment2, newFragment);
            //transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();

        } else {
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

    }

    @Override
    public void onClickItemListenner(Bundle bundle) {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            DetailFragment newFragment = new DetailFragment();
            newFragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        } else {
            DetailFragment newFragment = new DetailFragment();
            newFragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment2, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

    }
}
